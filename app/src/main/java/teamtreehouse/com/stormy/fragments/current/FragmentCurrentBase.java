package teamtreehouse.com.stormy.fragments.current;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentTabBase;
import teamtreehouse.com.stormy.model.Current;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/18/2016.
 */

public abstract class FragmentCurrentBase extends FragmentTabBase implements
        FragmentCurrentView {

    protected static final String FORECAST_CURRENT = "FORECAST_CURRENT";
    protected abstract int getLayoutId();

    private FragmentActivity mFragmentActivity;
    private FragmentCurrentPresenter mPresenter;
    private Current mCurrent;
    private String mTimezone;

    @BindView(R.id.locationIconImageView) ImageView mIconImageView;
    @BindView(R.id.timeTextView) TextView mTimeTextView;
    @BindView(R.id.temperatureMaxTextView) TextView mTemperatureTextView;
    @BindView(R.id.humidityTextView) TextView mHumidityTextView;
    @BindView(R.id.precipitationTextView) TextView mPrecipitationTextView;
    @BindView(R.id.summaryTextView) TextView mSummaryTextView;

    public static FragmentCurrentBase newInstance(Class<?> fType, Current forecastCurrent, String timezone) {

        FragmentCurrentBase fragment = null;

        if(fType.equals(FragmentCurrentPhone.class)) {

            fragment = new FragmentCurrentPhone();
        }
        else if(fType.equals(FragmentCurrentTablet.class)) {

            fragment = new FragmentCurrentTablet();
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable(FORECAST_CURRENT, forecastCurrent);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentActivity = (FragmentActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mCurrent = getArguments().getParcelable(FORECAST_CURRENT);
        mTimezone = getArguments().getString(Forecast.FORECAST_TIMEZONE);
        mPresenter = new FragmentCurrentPresenter(this);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);

        ButterKnife.bind(this, view);

        updateDisplay(mCurrent, mTimezone);
        return view;
    }

    @Override
    public void setTemperatureTextView(double temperature) {

        String value = getFormattedValue("%.1f", temperature);
        mTemperatureTextView.setText(value);
    }

    @Override
    public void setTimeTextView(String time) {

        String timeFormat = getFormattedValue("At %s it will be", time);
        mTimeTextView.setText(timeFormat);
    }

    @Override
    public void setHumidity(double humidity) {

        String humidtyFormat = getFormattedValue("%.2f", humidity);
        mHumidityTextView.setText(humidtyFormat);
    }

    @Override
    public void setPrecipitationTextView(int precip) {

        String precipFormat = getFormattedValue("%d %%", precip);
        mPrecipitationTextView.setText(precipFormat);
    }

    @Override
    public void setSummaryTextView(String summary) {

        mSummaryTextView.setText(summary);
    }

    @Override
    public void setIconImageView(int iconId) {

        Drawable drawable = ContextCompat.getDrawable(mFragmentActivity, iconId);
        mIconImageView.setImageDrawable(drawable);
    }

    private String getFormattedValue(String format, Object... args) {

        return String.format(Locale.ENGLISH, format, args);
    }

    public void updateDisplay(Current current, String timezone) {

        String time = current.getFormattedTime(timezone);

        mPresenter.setTimeTextView(time);
        mPresenter.setTemperatureTextView(current.getTemperature());
        mPresenter.setHumidityTextView(current.getHumidity());
        mPresenter.setPrecipitationTextView(current.getPrecipChance());
        mPresenter.setSummaryTextView(current.getSummary());
        mPresenter.setIconImageView(current.getIconId());
    }

    @Override
    public String getTitle() {

        return "Currently";
    }
}
