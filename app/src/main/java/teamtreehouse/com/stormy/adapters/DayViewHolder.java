package teamtreehouse.com.stormy.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

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
    public void bind(DayData data, int position, String timezone) {

        String dayText = position == 0 ? "Today" : data.getDayOfTheWeek(timezone);
        mDayTextView.setText(dayText);

        mIconImageView.setImageResource(data.getIconId());

        String maxTempFormatted = temperatureFormat(data.getTemperatureMax());
        mTemperatureMaxTextView.setText(maxTempFormatted);

        String minTempFormatted = temperatureFormat(data.getTemperatureMin());
        mTemperatureMinTextView.setText(minTempFormatted);
    }

    @Override
    protected void setViews() {

        mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        mTemperatureMaxTextView = (TextView) itemView.findViewById(R.id.temperatureMaxTextView);
        mTemperatureMinTextView = (TextView) itemView.findViewById(R.id.temperatureMinTextView);
        mDayTextView = (TextView) itemView.findViewById(R.id.dayTextView);
    }

    private String temperatureFormat(double temperature) {

        return String.format(Locale.ENGLISH, "%d", (int)temperature);
    }
}
