package teamtreehouse.com.stormy.ui;

import android.location.LocationManager;
import android.support.annotation.NonNull;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;

import teamtreehouse.com.stormy.network.InternetInfo;
import teamtreehouse.com.stormy.asynctasks.ForecastAsyncTask;
import teamtreehouse.com.stormy.clients.ForecastClient;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.dialogs.InternetErrorDialog;
import teamtreehouse.com.stormy.dialogs.LocationStateDialog;
import teamtreehouse.com.stormy.dialogs.LocationNullDialog;
import teamtreehouse.com.stormy.fragments.FragmentForecastBase;
import teamtreehouse.com.stormy.fragments.FragmentForecastTablet;
import teamtreehouse.com.stormy.fragments.FragmentForecastPhone;
import teamtreehouse.com.stormy.model.WeatherPlace;
import teamtreehouse.com.stormy.model.Forecast;

public class MainActivity extends AppCompatActivity implements
        MainActivityView,
        ResultView,
        ForecastAsyncTask.ForecastListener,
        GoogleApiClient.OnConnectionFailedListener {

    public static final String FORECAST = "forecast";
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public static final int USER_PERMISSIONS_CODE = 10;
    private static final int USER_GPS_CODE = 100;

    private Forecast mForecast;
    private WeatherPlace mCurrentPlace;
    private InternetInfo mInternetInfo;
    private MainActivityPresenter mPresenter;
    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.refreshImageView) ImageView mRefreshImageView;
    @BindView(R.id.toolBar) Toolbar mToolBar;

    private boolean isTablet;

    @Override
    protected void attachBaseContext(Context newBase) {

        // Allow app to be able to handle more than the 64k limit of method references.
        // This is due to using Google's Auto Complete fragment
        MultiDex.install(this);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolBar);
        ButterKnife.bind(this);

        // Get config value upon creation to know if app is running in a Phone or Tablet
        isTablet = getResources().getBoolean(R.bool.isTablet);

        mPresenter = new MainActivityPresenter(this);

        mPresenter.setVisibility(mProgressBar, false);

        // Change the color programmitcally instead of xml due to API level.
        mPresenter.setProgressbarColor(Color.WHITE);
        mPresenter.setToolbarTextColor(Color.WHITE);
        mPresenter.setToolbarTitle("Search for a city...");

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mInternetInfo = new InternetInfo(manager);

        mCurrentPlace = new WeatherPlace();

        // Build a client to send a request for the current location of the device
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        // Check if the app is re-creating after an orientation change or home button press.
        // If that is the case, then we only want to retrieve the last place and forecast requested.
        if (savedInstanceState != null) {

            mForecast = savedInstanceState.getParcelable(Forecast.FORECAST_JSON);
            mCurrentPlace = savedInstanceState.getParcelable(WeatherPlace.WEATHER_PLACE_JSON);
            mPresenter.setToolbarTitle(mCurrentPlace.getCityFullName());

        } else {

            // If the app is starting for the first time, or after a back button press,
            // first check for the availability of the GPS
            LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                // If GPS is unavailable, then prompt the user to turn it on or not
                new LocationStateDialog()
                        .show(getSupportFragmentManager(), "location_error_dialog");
            }
            else {

                // If GPS is available, then request for the device's location.
                requestUserLocation(mGoogleApiClient);
            }
        }
    }

    // Method to set Main Activity's FrameLayout with the corresponding Fragment
    private void setActivityFragment(Forecast forecast) {

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // If the current device is a tablet, then insert a Tablet Fragment
        if(isTablet) {

            fragment = FragmentForecastBase.newInstance(
                FragmentForecastTablet.class,
                forecast
            );
        }
        // If the current device is a phone, then insert a Phone Fragment
        else {

            fragment = FragmentForecastBase.newInstance(
                FragmentForecastPhone.class,
                forecast
            );
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of the app after an orientation change or a home button press
        outState.putParcelable(Forecast.FORECAST_JSON, mForecast);
        outState.putParcelable(WeatherPlace.WEATHER_PLACE_JSON, mCurrentPlace);
    }

    public void requestUserLocation(GoogleApiClient client) {

        // First check if the app has permissions to use the location of the device.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Prompt the user with a built int dialog to turn on Location Permissions
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, USER_PERMISSIONS_CODE);

            return;
        }

        // If the app has the right permissions, then retrieve the device's location
        toggleRefresh();

        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(client, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {

            // Check if the response with the current location was successful
            if(placeLikelihoods.getStatus().isSuccess()) {

                // Get the first place from the buffer.
                LatLng latLong = placeLikelihoods.get(0).getPlace().getLatLng();
                mCurrentPlace.setCoordinates(latLong);

                // Get address from Geocoder class with the new lat long values
                Address address = getAddress(mCurrentPlace.getLatitude(), mCurrentPlace.getLongitude());

                mCurrentPlace.setLocality(address);
                mPresenter.setToolbarTitle(mCurrentPlace.getCityFullName());

                requestForecast(
                        mCurrentPlace.getLatitude(),
                        mCurrentPlace.getLongitude());
            }
            else {

                toggleRefresh();

                // If the response was unsuccessful, then prompt the user with a dialog explaining
                // that the current location could not be retrieved.
                new LocationNullDialog()
                    .show(getSupportFragmentManager(), "null_location_dialog");
            }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Check which permission was set by the user.
        // For this app though the only permission that will be prompted to the user will be Location Permission
        switch (requestCode) {

            case USER_PERMISSIONS_CODE:

                // Check if the user allowed the Location Permission to be set for this app
                // Use the first index in grantResults since we are only prompting for 1 permission
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // If permission was granted, then request for user location.
                    requestUserLocation(mGoogleApiClient);
                }
                break;
        }
    }

    // Method that will handle either Auto Complete fragment result or an Alert Dialog result.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode) {

            case PLACE_AUTOCOMPLETE_REQUEST_CODE:

                // Check if the user successfully selected a Place from the Auto Complete Fragment
                if (resultCode == RESULT_OK) {

                    // Get the place selected
                    Place place = PlaceAutocomplete.getPlace(this, data);

                    String cityName = place.getName().toString();

                    // Set the tool bar's title to the new place selected.
                    mPresenter.setToolbarTitle(cityName);

                    LatLng newCoordinates = place.getLatLng();

                    // Get the address from the selected place
                    Address address = getAddress(newCoordinates.latitude, newCoordinates.longitude);

                    // Check if the selected place is an existing place
                    // I've had situations where the app could not retrieve information about a place
                    // from the Auto Complete Fragment.
                    if(address != null) {

                        mCurrentPlace.setCoordinates(newCoordinates);
                        mCurrentPlace.setLocality(address);

                        toggleRefresh();
                        requestForecast(
                                mCurrentPlace.getLatitude(),
                                mCurrentPlace.getLongitude()
                        );
                    }
                    else {

                        // If the selected place was not found, then prompt the user with an alert
                        // dialog explaining what went wrong
                        new LocationNullDialog()
                            .show(getSupportFragmentManager(), "null_location_dialog");
                    }
                }
                break;

            case USER_GPS_CODE:

                // If the user turned on the GPS through the alert dialog, then request for the
                // location of the device
                requestUserLocation(mGoogleApiClient);
                break;
        }
    }

    @OnClick(R.id.locationSearchImageView)
    public void onLocationSearchImageClick(View view) {

        // Prompt the user with the Auto Complete Fragment when they click on the search icon
        startActivityForResult(PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }

    @OnClick(R.id.refreshImageView)
    public void onRefreshImageClick(View view){

        // Check if there is internet available
        if(mInternetInfo.isNetworkAvailable()) {

            // Check if there has been a place calculated before refreshing.
            if(mCurrentPlace.hasCoordinates()) {

                toggleRefresh();
                requestForecast(
                    mCurrentPlace.getLatitude(),
                    mCurrentPlace.getLongitude())
                ;
            }
            else {

                // If there is no place calculated, it means there is nothing to refresh
                // Prompt the user with an alert dialog explaining that they need a place selected
                // before refreshing.
                new LocationNullDialog()
                    .show(getSupportFragmentManager(), "location_null_error");
            }
        }
        else {

            // If there is no internet available, then prompt the user with an alert dialog
            // explaining there is no internet in the device.
            new InternetErrorDialog()
                .show(getSupportFragmentManager(), "internet_error_dialog");
        }
    }

    @Override
    public void onForecastRetrieved(Forecast forecast) {

        toggleRefresh();

        // Check if the async task was able to retrieve the forecast information
        if(forecast == null) {

            // If no internet is available, then no forecast can be retrieved
            // Prompt the user with an alert dialog explaining there is no internet in the device.
            new InternetErrorDialog()
                .show(getSupportFragmentManager(), "internet_error_dialog");
        }
        else {

            // If the forecast was retrieved successfully, then set the appropriate fragment
            // with the information
            mForecast = forecast;
            setActivityFragment(mForecast);
        }
    }

    @Override
    public void setVisibility(View view, boolean visibile) {

        view.setVisibility(visibile ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setToolbarTitle(String title) {

        mToolBar.setTitle(title);
    }

    @Override
    public void setToolbarTextColor(int color) {

        mToolBar.setTitleTextColor(color);
    }

    @Override
    public void startActivityForResult(String serviceName) {

        startActivityForResult(new Intent(serviceName), USER_GPS_CODE);
    }

    // Use this interface method to launch the Auto Complete Fragment either from Main Activity
    // or from an alert dialog
    @Override
    public void startActivityForResult(int requestCode) {

        try {

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
            startActivityForResult(intent, requestCode);
        }

        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProgressbarColor(int color) {

        mProgressBar.getIndeterminateDrawable()
            .setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    private void toggleRefresh() {

        if (mProgressBar.getVisibility() == View.INVISIBLE) {

            mPresenter.setVisibility(mProgressBar, true);
            mPresenter.setVisibility(mRefreshImageView, false);
        }
        else {

            mPresenter.setVisibility(mProgressBar, false);
            mPresenter.setVisibility(mRefreshImageView, true);
        }
    }

    private void requestForecast(double latitude, double longitude) {

        // Create a client to send a forecast request
        ForecastClient url = new ForecastClient.Builder()
                .withLatitude(latitude)
                .withLongitude(longitude)
                .create();

        // Send the request using an async task
        new ForecastAsyncTask(this)
                .execute(url);
    }

    private Address getAddress(double latitude, double longitude) {

        // Use the Geocoder to retrieve the location of the device.
        Geocoder mGeocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {

            List<Address> addresses = mGeocoder.getFromLocation(latitude, longitude, 1);

            // Check if there was an address object found
            if(addresses == null || addresses.isEmpty()) {

                // Inform the caller that a valid address was not found from the lat and long values
                return null;
            }

            // return the first address from the list
            return addresses.get(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}