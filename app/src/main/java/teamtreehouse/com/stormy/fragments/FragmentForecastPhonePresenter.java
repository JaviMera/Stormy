package teamtreehouse.com.stormy.fragments;

import android.support.v4.view.ViewPager;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentForecastPhonePresenter {

    private FragmentPagerView mView;

    public FragmentForecastPhonePresenter(FragmentPagerView view) {

        mView = view;
    }

    public void setPagerAdapter(FragmentTabBase... fragments) {

        mView.setPagerAdapter(fragments);
    }

    public void setTabLayout(ViewPager pager) {

        mView.setTabLayout(pager);
    }

    public void setPagerItem(int currentItem) {

        mView.setPagerItem(currentItem);
    }
}
