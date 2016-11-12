package teamtreehouse.com.stormy.ui.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;

import javier.com.stormy.fragments.ForecastCurrentFragment;
import javier.com.stormy.network.InternetInfo;
import javier.com.stormy.asynctasks.ForecastAsyncTask;
import javier.com.stormy.url.ForecastUrl;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.ui.AlertDialogFragment;
import teamtreehouse.com.stormy.weather.Forecast;

public class MainActivity extends AppCompatActivity implements
        MainActivityView,
        ForecastAsyncTask.ForecastListener {

    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";
    public static final String TIMEZONE = "TIMEZONE_FORECAST";
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private Forecast mForecast;
    private double mLatitude;
    private double mLongitude;
    private InternetInfo mInternetInfo;
    private MainActivityPresenter mPresenter;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.refreshImageView) ImageView mRefreshImageView;
    @BindView(R.id.toolBar) Toolbar mToolBar;

    @Override
    protected void attachBaseContext(Context newBase) {
        MultiDex.install(this);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter(this);
        mPresenter.setVisibility(mProgressBar, false);

        setSupportActionBar(mToolBar);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mInternetInfo = new InternetInfo(manager);

        mLatitude = 37.8267;
        mLongitude = -122.423;

        if (mInternetInfo.isNetworkAvailable()) {

            toggleRefresh();
            sendRequest(mLatitude, mLongitude);
        }
        else {

            alertUserAboutError();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1) {

            if(resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                mToolBar.setTitle(place.getName());
            }
        }
    }

    @OnClick(R.id.locationSearchImageView)
    public void onLocationSearchImageClick(View view) {

        try {

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        }

        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.refreshImageView)
    public void onRefreshImageClick(View view){

        if(mInternetInfo.isNetworkAvailable()) {

            toggleRefresh();
            sendRequest(mLatitude, mLongitude);
        }
        else {

            alertUserAboutError();
        }
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

    private void sendRequest(double latitude, double longitude) {

        ForecastUrl url = new ForecastUrl.Builder()
                .withLatitude(latitude)
                .withLongitude(longitude)
                .create();

        new ForecastAsyncTask(this)
                .execute(url);
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
}














