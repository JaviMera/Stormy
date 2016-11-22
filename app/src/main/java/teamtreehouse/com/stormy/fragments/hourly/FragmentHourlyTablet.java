package teamtreehouse.com.stormy.fragments.hourly;

import android.content.res.Configuration;

public class FragmentHourlyTablet extends FragmentHourly {

    // Override this method in order to always return Portrait mode, even though the tablet might
    // be in landscape. This is because the app should display the Portrait layout of Hour data despite the orientation
    @Override
    protected int getOrientation() {

        return Configuration.ORIENTATION_PORTRAIT;
    }
}
