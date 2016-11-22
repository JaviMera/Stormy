package teamtreehouse.com.stormy.fragments;

import android.support.v4.view.ViewPager;

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
