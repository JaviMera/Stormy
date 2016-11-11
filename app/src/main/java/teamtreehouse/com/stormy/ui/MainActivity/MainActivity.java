package teamtreehouse.com.stormy.ui.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;

import javier.com.stormy.InternetInfo;
import javier.com.stormy.asynctasks.ForecastAsyncTask;
import javier.com.stormy.url.ForecastUrl;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.ui.AlertDialogFragment;
import teamtreehouse.com.stormy.ui.DailyForecastActivity;
import teamtreehouse.com.stormy.ui.HourlyForecastActivity;
import teamtreehouse.com.stormy.weather.Current;
import teamtreehouse.com.stormy.weather.Forecast;

public class MainActivity extends AppCompatActivity implements
        MainActivityView,
        ForecastAsyncTask.ForecastListener {

    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";
    public static final String TIMEZONE = "TIMEZONE_FORECAST";

    private MainActivityPresenter mPresenter;

    private Forecast mForecast;
    private double mLatitude;
    private double mLongitude;
    private InternetInfo mInternetInfo;

    @BindView(R.id.timeLabel) TextView mTimeLabel;
    @BindView(R.id.temperatureLabel) TextView mTemperatureLabel;
    @BindView(R.id.humidityValue) TextView mHumidityValue;
    @BindView(R.id.precipValue) TextView mPrecipValue;
    @BindView(R.id.summaryLabel) TextView mSummaryLabel;
    @BindView(R.id.iconImageView) ImageView mIconImageView;
    @BindView(R.id.refreshImageView) ImageView mRefreshImageView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mInternetInfo = new InternetInfo(manager);
        mPresenter = new MainActivityPresenter(this);

        mPresenter.setVisibility(mProgressBar, false);

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

    private void updateDisplay(Forecast forecast) {

        Current current = forecast.getCurrent();

        mPresenter.setTemperatureTextView(current.getTemperature());

        String time = current.getFormattedTime(forecast.getTimezone());
        mPresenter.setTimeTextView(time);

        mPresenter.setHumidityTextView(current.getHumidity());
        mPresenter.setPrecipitationTextView(current.getPrecipChance());
        mPresenter.setSummaryTextView(current.getSummary());
        mPresenter.setIconImageView(current.getIconId());
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    @OnClick (R.id.dailyButton)
    public void startDailyActivity(View view) {
        Intent intent = new Intent(this, DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST, mForecast.getDailyForecast());
        intent.putExtra(TIMEZONE, mForecast.getTimezone());
        startActivity(intent);
    }

    @OnClick (R.id.hourlyButton)
    public void startHourlyActivity(View view) {
        Intent intent = new Intent(this, HourlyForecastActivity.class);
        intent.putExtra(HOURLY_FORECAST, mForecast.getHourlyForecast());
        startActivity(intent);
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
            updateDisplay(mForecast);
        }
    }

    @Override
    public void setVisibility(View view, boolean visibile) {

        view.setVisibility(visibile ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setTemperatureTextView(double temperature) {

        String value = getFormattedValue("%f", temperature);
        mTemperatureLabel.setText(value);
    }

    @Override
    public void setTimeTextView(String time) {

        String timeFormat = getFormattedValue("At %s it will be", time);
        mTimeLabel.setText(timeFormat);
    }

    @Override
    public void setHumidity(double humidity) {

        String humidtyFormat = getFormattedValue("%.2f", humidity);
        mHumidityValue.setText(humidtyFormat);
    }

    @Override
    public void setPrecipitationTextView(int precip) {

        String precipFormat = getFormattedValue("%d %%", precip);
        mPrecipValue.setText(precipFormat);
    }

    @Override
    public void setSummaryTextView(String summary) {

        mSummaryLabel.setText(summary);
    }

    @Override
    public void setIconImageView(int iconId) {

        Drawable drawable = ContextCompat.getDrawable(this, iconId);
        mIconImageView.setImageDrawable(drawable);
    }

    private String getFormattedValue(String format, Object... args) {

        return String.format(Locale.ENGLISH, format, args);
    }
}














