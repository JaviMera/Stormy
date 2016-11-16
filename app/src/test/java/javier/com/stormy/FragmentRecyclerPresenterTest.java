package javier.com.stormy;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentRecyclerPresenter;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentRecyclerView;

/**
 * Created by Javi on 11/16/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FragmentRecyclerPresenterTest {

    @Mock
    public FragmentRecyclerView mView;

    private FragmentRecyclerPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new FragmentRecyclerPresenter(mView);
    }

    @Test
    public void setRecyclerAdapter() throws Exception {

        // Arrange
        AdapterBase adapter = null;

        // Act
        mPresenter.setRecyclerAdapter(adapter);

        // Assert
        Mockito.verify(mView).setRecyclerAdapter(adapter);
    }

    @Test
    public void setRecyclerManager() throws Exception {

        // Arrange
        RecyclerView.LayoutManager manager = null;

        // Act
        mPresenter.setRecyclerManager(manager);

        // Assert
        Mockito.verify(mView).setRecyclerManager(manager);
    }

    @Test
    public void setRecyclerSize() throws Exception {

        // Arrange
        boolean isFixed = true;

        // Act
        mPresenter.setRecyclerSize(isFixed);

        // Assert
        Mockito.verify(mView).setRecyclerSize(isFixed);
    }
}
