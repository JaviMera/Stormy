package teamtreehouse.com.stormy.ui.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

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

    private Forecast mForecast;
    private double mLatitude;
    private double mLongitude;
    private InternetInfo mInternetInfo;
    private MainActivityPresenter mPresenter;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.refreshImageView) ImageView mRefreshImageView;
    @BindView(R.id.toolBar) Toolbar mToolBar;

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
            fragmentTransaction.add(R.id.fragmentContainer, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setVisibility(View view, boolean visibile) {

        view.setVisibility(visibile ? View.VISIBLE : View.INVISIBLE);
    }
}














