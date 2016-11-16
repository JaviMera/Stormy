package teamtreehouse.com.stormy.fragments.FragmentViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import teamtreehouse.com.stormy.fragments.FragmentForecastBase;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private FragmentForecastBase[] mFragments;

    public FragmentPagerAdapter(FragmentManager fm, FragmentForecastBase... fragments) {
        super(fm);

        mFragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mFragments[position].getTitle();
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments[position];
    }

    @Override
    public int getCount() {

        return mFragments.length;
    }
}
