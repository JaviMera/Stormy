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
    protected abstract String getPositiveText();
    protected abstract String getNegativeText();

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
                .setPositiveButton(getPositiveText(), this)
                .setNegativeButton(getNegativeText(), null);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
    }
}
