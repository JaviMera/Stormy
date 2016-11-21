package teamtreehouse.com.stormy.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/15/2016.
 */

public class HourViewHolderPortrait extends ViewHolderBase<HourData> {

    public TextView mTimeLabel;
    public TextView mSummaryLabel;
    public TextView mTemperatureLabel;
    public ImageView mIconImageView;


    public HourViewHolderPortrait(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(HourData data, int position, String timezone) {

        String timeText = position == 0 ? "Now" : data.getHour(timezone);
        mTimeLabel.setText(timeText);

        mSummaryLabel.setText(data.getSummary());

        String temperatureText = valueFormat("%.0f", data.getTemperature());
        mTemperatureLabel.setText(temperatureText);

        mIconImageView.setImageResource(data.getIconId());
    }

    @Override
    protected void setViews() {

        mTimeLabel = (TextView) itemView.findViewById(R.id.timeTextView);
        mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryTextView);
        mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureMaxTextView);
        mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
    }

}
