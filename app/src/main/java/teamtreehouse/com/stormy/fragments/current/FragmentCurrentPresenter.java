package teamtreehouse.com.stormy.fragments.current;

/**
 * Created by Javi on 11/11/2016.
 */

public class FragmentCurrentPresenter {

    private FragmentCurrentView mView;

    public FragmentCurrentPresenter(FragmentCurrentView view) {

        mView = view;
    }

    public void setTemperatureTextView(double temperature) {

        mView.setTemperatureTextView(temperature);
    }

    public void setTimeTextView(String time) {

        mView.setTimeTextView(time);
    }

    public void setHumidityTextView(double humidity) {

        mView.setHumidity(humidity);
    }

    public void setPrecipitationTextView(int precip) {

        mView.setPrecipitationTextView(precip);
    }

    public void setSummaryTextView(String summary) {

        mView.setSummaryTextView(summary);
    }

    public void setIconImageView(int iconId) {

        mView.setIconImageView(iconId);
    }
}
