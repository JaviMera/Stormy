package teamtreehouse.com.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.weather.DayData;
import teamtreehouse.com.stormy.weather.HourData;

/**
 * Created by benjakuben on 2/6/15.
 */
public class DayAdapter extends BaseAdapter {

    private final String mTimezone;
    private Context mContext;
    private DayData[] mItems;

    public DayAdapter(Context context, DayData[] items, String timezone) {
        mContext = context;
        mItems = items;
        mTimezone = timezone;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // we aren't going to use this. Tag items for easy reference
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        DayData dayData = mItems[position];

        holder.iconImageView.setImageResource(dayData.getIconId());
        holder.temperatureLabel.setText(dayData.getTemperatureMax() + "");

        if (position == 0) {
            holder.dayLabel.setText("Today");
        }
        else {
            holder.dayLabel.setText(dayData.getDayOfTheWeek(mTimezone));
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImageView; // public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }
}












