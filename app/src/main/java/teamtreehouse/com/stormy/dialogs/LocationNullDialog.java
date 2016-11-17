package teamtreehouse.com.stormy.dialogs;

/**
 * Created by Javi on 11/14/2016.
 */

public class LocationNullDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Location not found!";
    }

    @Override
    protected String getMessage() {

        return "Please search for a location.";
    }
}
