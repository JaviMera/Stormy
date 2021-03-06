package teamtreehouse.com.stormy.dialogs;

import android.content.DialogInterface;
import android.provider.Settings;

/**
 * Created by Javi on 11/14/2016.
 */

public class LocationStateDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Use Location?";
    }

    @Override
    protected String getMessage() {

        return "This app needs to use GPS to retrieve the device's current location";
    }

    @Override
    protected String getPositiveText() {

        return "Turn On";
    }

    @Override
    protected String getNegativeText() {

        return "Ignore";
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        mActivity.startActivityForResult(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }
}