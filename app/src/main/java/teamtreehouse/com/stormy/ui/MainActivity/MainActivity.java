package teamtreehouse.com.stormy.ui.MainActivity;

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
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
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
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;

import javier.com.stormy.fragments.ForecastCurrentFragment;
import javier.com.stormy.network.Coordinates;
import javier.com.stormy.network.InternetInfo;
import javier.com.stormy.asynctasks.ForecastAsyncTask;
import javier.com.stormy.url.ForecastClient;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.ui.AlertDialogFragment;
import teamtreehouse.com.stormy.weather.Forecast;

public class MainActivity extends AppCompatActivity implements
        MainActivityView,
        ForecastAsyncTask.ForecastListener,
        GoogleApiClient.OnConnectionFailedListener {

    private Forecast mForecast;
    private Coordinates mCoordinates;
    private InternetInfo mInternetInfo;
    private MainActivityPresenter mPresenter;
    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.refreshImageView)
    ImageView mRefreshImageView;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;

    @Override
    protected void attachBaseContext(Context newBase) {
        MultiDex.install(this);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolBar);

        ButterKnife.bind(this);

        mCoordinates = new Coordinates();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mPresenter = new MainActivityPresenter(this);

        mPresenter.setVisibility(mProgressBar, false);
        mPresenter.setProgressbarColor(Color.WHITE);
        mPresenter.setToolbarTextColor(Color.WHITE);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mInternetInfo = new InternetInfo(manager);

        requestUserLocation(mGoogleApiClient);
    }

    private void requestUserLocation(GoogleApiClient client) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, MainActivityExtras.USER_PERMISSIONS_CODE);

            return;
        }

        toggleRefresh();
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(client, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {

                // Get the first place from the buffer.
                LatLng latLong = placeLikelihoods.get(0).getPlace().getLatLng();

                // Store the coordinates in private fields, in order to access them later when refreshing
                mCoordinates.setLatitude(latLong.latitude);
                mCoordinates.setLongitude(latLong.longitude);

                Geocoder mGeocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {

                    List<Address> addresses = mGeocoder.getFromLocation(mCoordinates.getLatitude(), mCoordinates.getLongitude(), 1);

                    // Get the first address result from the list
                    Address address = addresses.get(0);
                    mPresenter.setToolbarTitle(address.getLocality() + ", " + address.getAdminArea());
                    sendWeatherRequest(mCoordinates.getLatitude(), mCoordinates.getLongitude());

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    requestUserLocation(mGoogleApiClient);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == MainActivityExtras.PLACE_AUTOCOMPLETE_REQUEST_CODE) {

            if(resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                String cityName = place.getName().toString();
                mPresenter.setToolbarTitle(cityName);

                LatLng newCoordinates = place.getLatLng();

                mCoordinates.setLatitude(newCoordinates.latitude);
                mCoordinates.setLongitude(newCoordinates.longitude);

                toggleRefresh();
                sendWeatherRequest(mCoordinates.getLatitude(), mCoordinates.getLongitude());
            }
        }
    }

    @OnClick(R.id.locationSearchImageView)
    public void onLocationSearchImageClick(View view) {

        try {

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
            startActivityForResult(intent, MainActivityExtras.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        }

        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.refreshImageView)
    public void onRefreshImageClick(View view){

        if(mInternetInfo.isNetworkAvailable()) {

            toggleRefresh();
            sendWeatherRequest(mCoordinates.getLatitude(), mCoordinates.getLongitude());
        }
        else {

            alertUserAboutError();
        }
    }

    @Override
    public void onForecastRetrieved(Forecast forecast) {

        if(forecast == null) {

            alertUserAboutError();
        }
        else {

            toggleRefresh();
            mForecast = forecast;

            ForecastCurrentFragment fragment = ForecastCurrentFragment.newInstance(forecast.getCurrent(), forecast.getTimezone());
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.commit();
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

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private void sendWeatherRequest(double latitude, double longitude) {

        ForecastClient url = new ForecastClient.Builder()
                .withLatitude(latitude)
                .withLongitude(longitude)
                .create();

        new ForecastAsyncTask(this)
                .execute(url);
    }

}














