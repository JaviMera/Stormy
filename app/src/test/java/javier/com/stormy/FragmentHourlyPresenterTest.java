package javier.com.stormy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import teamtreehouse.com.stormy.fragments.FragmentHourly.FragmentHourlyPresenter;
import teamtreehouse.com.stormy.fragments.FragmentHourly.FragmentHourlyView;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javier on 11/15/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FragmentHourlyPresenterTest {

    @Mock
    public FragmentHourlyView mView;

    private FragmentHourlyPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new FragmentHourlyPresenter(mView);
    }

    @Test
    public void setRecyclerAdapter() throws Exception {

        // Arrange
        HourData[] data = null;

        // Act
        mPresenter.setRecyclerAdapter(data);

        // Assert
        Mockito.verify(mView).setRecyclerAdapter(data);
    }

    @Test
    public void setRecyclerLayout() throws Exception {

        // Arrange
        int orientation = 1;

        // Act
        mPresenter.setRecyclerLayout(orientation);

        // Assert
        Mockito.verify(mView).setRecyclerLayout(orientation);
    }

    @Test
    public void setRecyclerFixedSize() throws Exception {

        // Arrange
        boolean isFixed = true;

        // Act
        mPresenter.setRecyclerFixedSize(isFixed);

        // Assert
        Mockito.verify(mView).setRecyclerFixedSize(isFixed);
    }
}
