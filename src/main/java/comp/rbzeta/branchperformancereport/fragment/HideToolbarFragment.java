package comp.rbzeta.branchperformancereport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.contract.BPRContract;

/**
 * Created by Robyn on 20/09/2016.
 */

public class HideToolbarFragment extends Fragment {

    private TextView textNetworkTimeout, textNetworkOffline, textNetworkDevice;

    public HideToolbarFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_hide, container,false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
