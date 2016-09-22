package comp.rbzeta.branchperformancereport.recyclerview;

import android.view.View;

/**
 * Created by Robyn on 17/09/2016.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
