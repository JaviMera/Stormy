package teamtreehouse.com.stormy.dialogs;

import android.content.DialogInterface;
import android.provider.Settings;

/**
 * Created by Javi on 11/14/2016.
 */

public class LocationStateDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Device Location Error!";
    }

    @Override
    protected String getMessage() {

        return "Seems that your GPS is turned off. Turn on?";
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        mActivity.startActivityForResult(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }
}
