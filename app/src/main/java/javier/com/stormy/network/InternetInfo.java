package javier.com.stormy.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Javi on 11/11/2016.
 */

public class InternetInfo {

    private ConnectivityManager mManager;

    public InternetInfo(ConnectivityManager manager) {

        mManager = manager;
    }

    public boolean isNetworkAvailable() {

        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }
}
