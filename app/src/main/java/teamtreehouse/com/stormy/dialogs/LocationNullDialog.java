package teamtreehouse.com.stormy.dialogs;

import android.content.DialogInterface;

import teamtreehouse.com.stormy.ui.MainActivity;

/**
 * Created by Javi on 11/14/2016.
 */

public class LocationNullDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Select a city";
    }

    @Override
    protected String getMessage() {

        return "A city needs to be selected before refreshing.";
    }

    @Override
    protected String getPositiveText() {

        return "Search";
    }

    @Override
    protected String getNegativeText() {

        return "Ignore";
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        mActivity.startActivityForResult(MainActivity.PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }
}
