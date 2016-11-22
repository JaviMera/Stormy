package teamtreehouse.com.stormy.dialogs;

/**
 * Created by Javi on 11/14/2016.
 */

public class InternetErrorDialog extends AlertDialogBase {

    @Override
    protected String getTitle() {

        return "Internet Error!";
    }

    @Override
    protected String getMessage() {

        return "This app requires internet.";
    }

    @Override
    protected String getPositiveText() {

        return "OK";
    }

    @Override
    protected String getNegativeText() {

        return "";
    }
}
