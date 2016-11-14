package teamtreehouse.com.stormy.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import teamtreehouse.com.stormy.R;

/**
 * Created by benjakuben on 12/3/14.
 */
public abstract class AlertDialogBase extends DialogFragment {

    protected abstract String getTitle();
    protected abstract String getMessage();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage(getMessage())
                .setPositiveButton(context.getString(R.string.error_ok_button_text), null);

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
