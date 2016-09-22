package comp.rbzeta.branchperformancereport.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Robyn on 18/09/2016.
 */
public class UIHelper {

    public static void showToastLong(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showCustomSnackBar(View view, String msg, int color){

        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView =
                (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();

    }

    public static void requestFocus(Window window, View view) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
