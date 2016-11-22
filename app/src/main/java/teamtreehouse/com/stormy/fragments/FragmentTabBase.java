package teamtreehouse.com.stormy.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Javi on 11/14/2016.
 */
// Abstract type to represent any fragment that will be part of a view pager.
// This will be the case for current, daily and hourly fragments for phone devices.
public abstract class FragmentTabBase extends Fragment {

    public abstract String getTitle();
}
