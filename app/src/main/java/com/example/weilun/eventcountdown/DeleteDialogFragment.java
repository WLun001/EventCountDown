package com.example.weilun.eventcountdown;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.Toast;

/**
 * Created by Wei Lun on 8/4/2017.
 */

public class DeleteDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity());
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventDBQueries dbQueries = new EventDBQueries(new EventDBHelper(getActivity()));
                        dbQueries.deleteAll();
                        Toast.makeText(getActivity(), R.string.delete_success, Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).onResume();
                    }
                });
        return builder.create();
    }
}
