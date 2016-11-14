package teamtreehouse.com.stormy.fragments.FragmentViewPager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.ForecastFragmentBase;
import teamtreehouse.com.stormy.fragments.FragmentCurrent.FragmentCurrentCurrent;
import teamtreehouse.com.stormy.fragments.FragmentDaily;
import teamtreehouse.com.stormy.fragments.FragmentHourly;
import teamtreehouse.com.stormy.weather.Current;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentViewPager extends Fragment {

    private FragmentActivity mActivity;
    private String mTimezone;
    private Current mCurrentWeather;

    @BindView(R.id.viewPager) ViewPager mViewPager;
    @BindView(R.id.tabLayout) TabLayout mTabLayout;

    public static FragmentViewPager newInstance(Current currentWeather, String timezone) {

        FragmentViewPager fragment = new FragmentViewPager();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FragmentCurrentCurrent.FORECAST_CURRENT, currentWeather);
        bundle.putString(FragmentCurrentCurrent.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mCurrentWeather = getArguments().getParcelable(FragmentCurrentCurrent.FORECAST_CURRENT);
        mTimezone = getArguments().getString(FragmentCurrentCurrent.FORECAST_TIMEZONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ButterKnife.bind(this, view);

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            ForecastFragmentBase[] mFragments = new ForecastFragmentBase[]{
                FragmentCurrentCurrent.newInstance(mCurrentWeather, mTimezone),
                FragmentHourly.newInstance(),
                FragmentDaily.newInstance()
            };

            @Override
            public Fragment getItem(int position) {

                return mFragments[position];
            }

            @Override
            public int getCount() {

                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return mFragments[position].getTitle();
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }
}
