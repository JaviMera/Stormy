package teamtreehouse.com.stormy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Javi on 11/14/2016.
 */
public abstract class ForecastFragmentBase extends Fragment{

    protected abstract String getTitle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }
}
