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
import teamtreehouse.com.stormy.fragments.FragmentCurrent.FragmentCurrentPhone;
import teamtreehouse.com.stormy.fragments.FragmentForecastBase;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentDaily;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentDailyPhone;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentHourlyPhone;
import teamtreehouse.com.stormy.ui.MainActivity.MainActivity;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/14/2016.
 */

public class FragmentForecastPhone extends Fragment implements FragmentPagerView{

    private FragmentActivity mActivity;
    private Forecast mForecast;

    private FragmentPagerPresenter mPresenter;

    @BindView(R.id.viewPager) ViewPager mViewPager;
    @BindView(R.id.tabLayout) TabLayout mTabLayout;

    public static FragmentForecastPhone newInstance(Forecast forecast) {

        FragmentForecastPhone fragment = new FragmentForecastPhone();

        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.FORECAST, forecast);
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
        mForecast = getArguments().getParcelable(MainActivity.FORECAST);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_phone, container, false);

        ButterKnife.bind(this, view);
        mPresenter = new FragmentPagerPresenter(this);

        mPresenter.setPagerAdapter(
            FragmentCurrentPhone.newInstance(
                mForecast.getCurrent(),
                mForecast.getTimezone())
            ,
            FragmentHourlyPhone.newInstance(
                FragmentHourlyPhone.class,
                mForecast.getHourlyForecast())
            ,
            FragmentDaily.newInstance(
                FragmentDailyPhone.class,
                mForecast.getDailyForecast(),
                mForecast.getTimezone())
        );

        mPresenter.setTabLayout(mViewPager);

        return view;
    }

    @Override
    public void setPagerAdapter(FragmentForecastBase... fragments) {

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
