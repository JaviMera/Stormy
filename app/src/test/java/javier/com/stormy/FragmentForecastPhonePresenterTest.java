package javier.com.stormy;

import android.support.v4.view.ViewPager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import teamtreehouse.com.stormy.fragments.FragmentTabBase;
import teamtreehouse.com.stormy.fragments.FragmentForecastPhonePresenter;
import teamtreehouse.com.stormy.fragments.FragmentPagerView;

/**
 * Created by Javi on 11/14/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class FragmentForecastPhonePresenterTest {

    @Mock
    public FragmentPagerView mView;

    private FragmentForecastPhonePresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new FragmentForecastPhonePresenter(mView);
    }

    @Test
    public void setPagerAdapter() throws Exception {

        // Arrange
        FragmentTabBase[] fragments = null;

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

    @Test
    public void setPagerCurrentItem() throws Exception {

        // Arrange
        int currentItem = 2;

        // Act
        mPresenter.setPagerItem(currentItem);

        // Assert
        Mockito.verify(mView).setPagerItem(currentItem);
    }
}
