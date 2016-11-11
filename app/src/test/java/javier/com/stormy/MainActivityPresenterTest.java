package javier.com.stormy;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javier.com.stormy.ui.MainActivityView;
import teamtreehouse.com.stormy.ui.MainActivity.MainActivityPresenter;

/**
 * Created by Javi on 11/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    private MainActivityPresenter mPresenter;

    @Mock
    public MainActivityView mView;

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
}
