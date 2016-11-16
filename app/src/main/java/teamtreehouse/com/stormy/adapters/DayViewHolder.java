package teamtreehouse.com.stormy.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.DayData;

/**
 * Created by Javi on 11/15/2016.
 */

public class DayViewHolder extends ViewHolderBase<DayData> {

    ImageView mIconImageView;
    TextView mTemperatureMaxTextView;
    TextView mTemperatureMinTextView;
    TextView mDayTextView;

    public DayViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(DayData data) {

        mIconImageView.setImageResource(data.getIconId());
        mTemperatureMaxTextView.setText(String.valueOf(data.getTemperatureMax()));
        mTemperatureMinTextView.setText(String.valueOf(data.getTemperatureMin()));
        mDayTextView.setText("Some day");
    }

    @Override
    protected void setViews() {

        mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        mTemperatureMaxTextView = (TextView) itemView.findViewById(R.id.temperatureMaxTextView);
        mTemperatureMinTextView = (TextView) itemView.findViewById(R.id.temperatureMinTextView);
        mDayTextView = (TextView) itemView.findViewById(R.id.dayTextView);
    }
}
