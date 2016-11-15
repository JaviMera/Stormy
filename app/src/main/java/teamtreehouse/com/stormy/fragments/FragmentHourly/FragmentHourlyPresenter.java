package teamtreehouse.com.stormy.fragments.FragmentHourly;

import teamtreehouse.com.stormy.weather.HourData;

/**
 * Created by Javier on 11/15/2016.
 */

public class FragmentHourlyPresenter {

    private FragmentHourlyView mView;

    public FragmentHourlyPresenter(FragmentHourlyView view) {

        mView = view;
    }

    public void setRecyclerAdapter(HourData[] data) {

        mView.setRecyclerAdapter(data);
    }

    public void setRecyclerLayout(int orientation) {

        mView.setRecyclerLayout(orientation);
    }

    public void setRecyclerFixedSize(boolean isFixed) {

        mView.setRecyclerFixedSize(isFixed);
    }
}
