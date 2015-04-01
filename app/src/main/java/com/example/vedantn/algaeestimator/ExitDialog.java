package com.example.vedantn.algaeestimator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by vedant.n on 2/28/2015.
 */
public class ExitDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder exitDialog = new AlertDialog.Builder(getActivity());

        exitDialog.setTitle("Exit");
        exitDialog.setMessage("Are you sure you want to exit?");



        exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(),"OK",Toast.LENGTH_LONG).show();
                //getActivity().finish();
                //return;

            }
        });

        exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              // return;
            }
        });

        return super.onCreateDialog(savedInstanceState);
    }
}
