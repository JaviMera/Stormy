package teamtreehouse.com.stormy.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.ui.MainActivity;
import teamtreehouse.com.stormy.ui.ResultView;

/**
 * Created by benjakuben on 12/3/14.
 */
public abstract class AlertDialogBase extends DialogFragment implements DialogInterface.OnClickListener {

    protected ResultView mActivity;
    protected abstract String getTitle();
    protected abstract String getMessage();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (MainActivity) getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage(getMessage())
                .setPositiveButton(context.getString(R.string.error_ok_button_text), this)
                .setNegativeButton("Ignore", null);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
