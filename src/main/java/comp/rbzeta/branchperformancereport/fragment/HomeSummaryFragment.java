package comp.rbzeta.branchperformancereport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import comp.rbzeta.branchperformancereport.R;
/**
 * Created by Robyn on 22/09/2016.
 */

public class HomeSummaryFragment extends Fragment{

   private View mView;

    public HomeSummaryFragment(){

    }
    public static HomeSummaryFragment newInstance(Bundle bundle) {
        HomeSummaryFragment f = new HomeSummaryFragment();
        f.setArguments(bundle);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_summary, container,false);

        this.mView = view;

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }
}