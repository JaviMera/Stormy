package javier.com.stormy.fragments;

import android.view.View;

/**
 * Created by Javi on 11/11/2016.
 */
public interface ForecastCurrentFragmentView {

    void setTemperatureTextView(double temperature);
    void setTimeTextView(String time);
    void setHumidity(double humidity);
    void setPrecipitationTextView(int precip);
    void setSummaryTextView(String summary);
    void setIconImageView(int iconId);
    void setLocationTextView(String timezone);
}
