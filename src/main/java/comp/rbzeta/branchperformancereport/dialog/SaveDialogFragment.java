package comp.rbzeta.branchperformancereport.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.activity.DataNetworkActivity;
import comp.rbzeta.branchperformancereport.activity.DetailAnswerActivity;

/**
 * Created by Robyn on 14/09/2016.
 */
public class SaveDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(getActivity());

        alertBuilder.setMessage(R.string.text_question_simpan)
                .setPositiveButton(R.string.string_yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //((DetailAnswerActivity)getActivity()).doPositiveClick();
                        ((DataNetworkActivity)getActivity()).doPositiveClick();


                    }
                })
                .setNegativeButton(R.string.string_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //((DetailAnswerActivity)getActivity()).doNegativeClick();
                    }
                });
        return alertBuilder.create();
    }
}
