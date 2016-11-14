package teamtreehouse.com.stormy.dialogs;

/**
 * Created by Javi on 11/14/2016.
 */

public class LocationErrorDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Device Location Error!";
    }

    @Override
    protected String getMessage() {

        return "We were unable to retrieve your location.Try searching for it :D";
    }
}
