package teamtreehouse.com.stormy.fragments.FragmentViewPager;

import android.support.v4.view.ViewPager;

import teamtreehouse.com.stormy.fragments.ForecastFragmentBase;

/**
 * Created by Javi on 11/14/2016.
 */
public interface FragmentPagerView {

    void setPagerAdapter(ForecastFragmentBase... fragments);
    void setTabLayout(ViewPager pager);
}
