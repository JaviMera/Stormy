package javier.com.stormy;

import android.support.v4.view.ViewPager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import teamtreehouse.com.stormy.fragments.FragmentForecastBase;
import teamtreehouse.com.stormy.fragments.FragmentViewPager.FragmentPagerPresenter;
import teamtreehouse.com.stormy.fragments.FragmentViewPager.FragmentPagerView;

/**
 * Created by Javi on 11/14/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class FragmentPagerPresenterTest {

    @Mock
    public FragmentPagerView mView;

    private FragmentPagerPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new FragmentPagerPresenter(mView);
    }

    @Test
    public void setPagerAdapter() throws Exception {

        // Arrange
        FragmentForecastBase[] fragments = null;

        // Act
        mPresenter.setPagerAdapter(fragments);

        // Assert
        Mockito.verify(mView).setPagerAdapter(fragments);
    }

    @Test
    public void setTabLayout() throws Exception {

        // Arrange
        ViewPager pager = null;

        // Act
        mPresenter.setTabLayout(pager);

        // Assert
        Mockito.verify(mView).setTabLayout(pager);
    }
}
