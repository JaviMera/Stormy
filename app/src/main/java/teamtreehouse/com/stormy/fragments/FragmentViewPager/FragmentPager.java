package teamtreehouse.com.stormy.fragments.FragmentViewPager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentBase;
import teamtreehouse.com.stormy.fragments.FragmentCurrent.FragmentCurrent;
import teamtreehouse.com.stormy.fragments.FragmentDaily;
import teamtreehouse.com.stormy.fragments.FragmentHourly;
import teamtreehouse.com.stormy.weather.Current;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentPager extends Fragment implements FragmentPagerView{

    private FragmentActivity mActivity;
    private String mTimezone;
    private Current mCurrentWeather;

    private FragmentPagerPresenter mPresenter;

    @BindView(R.id.viewPager) ViewPager mViewPager;
    @BindView(R.id.tabLayout) TabLayout mTabLayout;

    public static FragmentPager newInstance(Current currentWeather, String timezone) {

        FragmentPager fragment = new FragmentPager();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FragmentCurrent.FORECAST_CURRENT, currentWeather);
        bundle.putString(FragmentCurrent.FORECAST_TIMEZONE, timezone);
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

        mCurrentWeather = getArguments().getParcelable(FragmentCurrent.FORECAST_CURRENT);
        mTimezone = getArguments().getString(FragmentCurrent.FORECAST_TIMEZONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ButterKnife.bind(this, view);
        mPresenter = new FragmentPagerPresenter(this);

        mPresenter.setPagerAdapter(FragmentCurrent.newInstance(
            mCurrentWeather, mTimezone),
            FragmentHourly.newInstance(),
            FragmentDaily.newInstance());

        mPresenter.setTabLayout(mViewPager);

        return view;
    }

    @Override
    public void setPagerAdapter(FragmentBase... fragments) {

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(
            getChildFragmentManager(),
            fragments);

        mViewPager.setAdapter(adapter);
    }

    @Override
    public void setTabLayout(ViewPager pager) {

        mTabLayout.setupWithViewPager(pager);
    }
}
