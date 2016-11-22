package teamtreehouse.com.stormy.fragments;

import android.support.v4.view.ViewPager;

import teamtreehouse.com.stormy.fragments.FragmentTabBase;

/**
 * Created by Javi on 11/14/2016.
 */
public interface FragmentPagerView {

    void setPagerAdapter(FragmentTabBase... fragments);
    void setTabLayout(ViewPager pager);
    void setPagerItem(int currentItem);
}
