package teamtreehouse.com.stormy.fragments.FragmentHourly;

import teamtreehouse.com.stormy.weather.HourData;

/**
 * Created by Javier on 11/15/2016.
 */

public interface FragmentHourlyView {

    void setRecyclerAdapter(HourData[] data);
    void setRecyclerLayout(int orientation);
    void setRecyclerFixedSize(boolean isFixed);
}
