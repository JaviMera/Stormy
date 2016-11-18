package teamtreehouse.com.stormy.fragments.FragmentCurrent;

import android.view.View;

/**
 * Created by Javi on 11/11/2016.
 */
public interface FragmentCurrentView {

    void setTemperatureTextView(double temperature);
    void setTimeTextView(String time);
    void setHumidity(double humidity);
    void setPrecipitationTextView(int precip);
    void setSummaryTextView(String summary);
    void setIconImageView(int iconId);
}
