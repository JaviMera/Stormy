package teamtreehouse.com.stormy.fragments.FragmentViewPager;

import android.support.v4.view.ViewPager;

import teamtreehouse.com.stormy.fragments.FragmentTabBase;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentPagerPresenter {

    private FragmentPagerView mView;

    public FragmentPagerPresenter(FragmentPagerView view) {

        mView = view;
    }

    public void setPagerAdapter(FragmentTabBase... fragments) {

        mView.setPagerAdapter(fragments);
    }

    public void setTabLayout(ViewPager pager) {

        mView.setTabLayout(pager);
    }
}
