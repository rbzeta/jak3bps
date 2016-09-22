package comp.rbzeta.branchperformancereport.util;

import android.text.TextUtils;

/**
 * Created by Robyn on 17/09/2016.
 */
public class ValidatorHelper {

    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
