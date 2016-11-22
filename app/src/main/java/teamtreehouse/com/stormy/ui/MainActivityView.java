package teamtreehouse.com.stormy.ui;

import android.view.View;

/**
 * Created by Javi on 11/11/2016.
 */

public interface MainActivityView {

    void setVisibility(View view, boolean visibile);
    void setToolbarTitle(String title);
    void setToolbarTextColor(int colorResource);
    void setProgressbarColor(int color);
}
