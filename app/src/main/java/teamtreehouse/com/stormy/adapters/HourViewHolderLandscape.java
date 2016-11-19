package teamtreehouse.com.stormy.adapters;

import android.view.View;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/18/2016.
 */

public class HourViewHolderLandscape extends HourViewHolderPortrait{

    private TextView mHumidity;
    private TextView mWindSpeed;
    private TextView mWindBearing;

    public HourViewHolderLandscape(View itemView) {
        super(itemView);

    }

    @Override
    protected void setViews() {

        super.setViews();

        mHumidity = (TextView) itemView.findViewById(R.id.hourHumidityTextView);
        mWindSpeed = (TextView) itemView.findViewById(R.id.hourWindSpeedTextView);
        mWindBearing = (TextView) itemView.findViewById(R.id.hourWindBearTextView);
    }

    @Override
    public void bind(HourData data, int position, String timezone) {
        super.bind(data, position, timezone);

        String humidityText = valueFormat("%.2f", data.getHumidity());
        mHumidity.setText(humidityText);

        String windSpeedText = valueFormat("%.2f", data.getWindSpeed());
        mWindSpeed.setText(windSpeedText);

        String windBearingText = valueFormat("%d", data.getWindBearing());
        mWindBearing.setText(windBearingText);
    }
}
