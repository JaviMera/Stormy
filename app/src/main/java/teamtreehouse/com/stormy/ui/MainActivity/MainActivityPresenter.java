package teamtreehouse.com.stormy.ui.MainActivity;

import android.view.View;

import javier.com.stormy.ui.MainActivityView;

/**
 * Created by Javi on 11/11/2016.
 */

public class MainActivityPresenter {

    private MainActivityView mView;

    public MainActivityPresenter(MainActivityView view) {

        mView = view;
    }

    public void setVisibility(View view, boolean visible) {

        mView.setVisibility(view, visible);
    }
}
