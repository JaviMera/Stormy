package teamtreehouse.com.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.weather.DayData;

/**
 * Created by Javi on 11/15/2016.
 */
public class FragmentDailyAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private DayData[] mData;
    private String mTimezone;

    public FragmentDailyAdapter(Context context, DayData[] data, String timezone) {

        mContext = context;
        mData = data;
        mTimezone = timezone;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item, parent, false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((DailyViewHolder)holder).bind(mData[position]);
    }

    @Override
    public int getItemCount() {

        return mData.length;
    }

    private class DailyViewHolder extends RecyclerView.ViewHolder {

        ImageView mIconImageView;
        TextView mTemperatureMaxTextView;
        TextView mTemperatureMinTextView;
        TextView mDayTextView;

        public DailyViewHolder(View itemView) {
            super(itemView);

            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
            mTemperatureMaxTextView = (TextView) itemView.findViewById(R.id.temperatureMaxTextView);
            mTemperatureMinTextView = (TextView) itemView.findViewById(R.id.temperatureMinTextView);
            mDayTextView = (TextView) itemView.findViewById(R.id.dayTextView);
        }

        public void bind(DayData dayData) {

            mIconImageView.setImageResource(dayData.getIconId());
            mTemperatureMaxTextView.setText(String.valueOf(dayData.getTemperatureMax()));
            mTemperatureMinTextView.setText(String.valueOf(dayData.getTemperatureMin()));
            mDayTextView.setText(dayData.getDayOfTheWeek(mTimezone));
        }
    }
}
