package javier.com.stormy;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import teamtreehouse.com.stormy.ui.MainActivity.MainActivityPresenter;
import teamtreehouse.com.stormy.ui.MainActivity.MainActivityView;

/**
 * Created by Javi on 11/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    @Mock
    public MainActivityView mView;

    private MainActivityPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new MainActivityPresenter(mView);
    }

    @Test
    public void setProgressBarVisibilityTest() throws Exception {

        // Arrange
        View view = null;
        boolean visibile = true;

        // Act
        mPresenter.setVisibility(view, visibile);

        // Assertw
        Mockito.verify(mView).setVisibility(view, visibile);
    }

    @Test
    public void setToolBarTitle() throws Exception {

        // Arrange
        String title = "rofl";

        // Act
        mPresenter.setToolbarTitle(title);

        // Assert
        Mockito.verify(mView).setToolbarTitle(title);
    }

    @Test
    public void setToolbarTextColor() throws Exception {

        // Arrange
        int colorResource = 123;

        // Act
        mPresenter.setToolbarTextColor(colorResource);

        // Assert
        Mockito.verify(mView).setToolbarTextColor(colorResource);
    }
}
