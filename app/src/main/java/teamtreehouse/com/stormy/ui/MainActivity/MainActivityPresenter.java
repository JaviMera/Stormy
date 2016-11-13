package teamtreehouse.com.stormy.ui.MainActivity;

import android.view.View;

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

    public void setToolbarTitle(String title) {

        mView.setToolbarTitle(title);
    }

    public void setToolbarTextColor(int colorResource) {

        mView.setToolbarTextColor(colorResource);
    }
}
