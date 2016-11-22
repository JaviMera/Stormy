package teamtreehouse.com.stormy.adapters.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.WeatherData;

/**
 * Created by Javi on 11/18/2016.
 */

public abstract class WeatherDataViewHolderLandscape<T extends WeatherData> extends ViewHolderBase<T>{

    private TextView mTitle;
    private TextView mSummary;
    private TextView mTemperatureValue;
    private ImageView mTemperatureIcon;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private TextView mWindBearing;

    protected abstract String getTemperatureTitle(T data, String timezone);
    protected abstract String getTemperatureValue(T data);

    public WeatherDataViewHolderLandscape(View itemView) {
        super(itemView);
    }

    @Override
    protected void setViews() {

        mTitle = (TextView) itemView.findViewById(R.id.titleTextView);
        mSummary = (TextView) itemView.findViewById(R.id.temperatureSummary);
        mTemperatureValue = (TextView) itemView.findViewById(R.id.temperatureTextView);
        mTemperatureIcon = (ImageView) itemView.findViewById(R.id.iconImageView);
        mHumidity = (TextView) itemView.findViewById(R.id.humidityTextView);
        mWindSpeed = (TextView) itemView.findViewById(R.id.windSpeedTextView);
        mWindBearing = (TextView) itemView.findViewById(R.id.windBearTextView);
    }

    @Override
    public void bind(T data, int position, String timezone) {

        mTitle.setText(getTemperatureTitle(data, timezone));
        mSummary.setText(data.getSummary());
        mTemperatureValue.setText(getTemperatureValue(data));
        mTemperatureIcon.setImageResource(data.getIconId());

        String humidityText = valueFormat("%.2f", data.getHumidity());
        mHumidity.setText(humidityText);

        String windSpeedText = valueFormat("%.2f", data.getWindSpeed());
        mWindSpeed.setText(windSpeedText);

        String windBearingText = valueFormat("%d", data.getWindBearing());
        mWindBearing.setText(windBearingText);
    }
}
