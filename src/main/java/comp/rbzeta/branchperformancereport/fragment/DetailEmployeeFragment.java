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

public class DetailEmployeeFragment extends Fragment {

    private TextView textBranch, textPersonalNumber, textEmpName, textEmpJob;

    public DetailEmployeeFragment(){

    }
    public static DetailEmployeeFragment newInstance(Bundle bundle) {
        DetailEmployeeFragment f = new DetailEmployeeFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail_employee, container,false);
        textBranch = (TextView)view.findViewById(R.id.textNetworkTimeout);
        textPersonalNumber = (TextView)view.findViewById(R.id.textNetworkOffline);
        textEmpName = (TextView)view.findViewById(R.id.textNetworkDevice);
        textEmpJob = (TextView)view.findViewById(R.id.textLasMenu);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*Bundle empBundle = getArguments();
        //String t = empBundle.getString("text_branch_name");

        //String t = empBundle.getString("text_branch_name");
        //textBranch.setText(t);*/

        Bundle extras = getActivity().getIntent().getExtras();
        textBranch.setText(extras.getString(BPRContract.BPR.COLUMN_BRANCH_NAME));
        textPersonalNumber.setText(extras.getString(BPRContract.BPR.COLUMN_PERSONAL_NUMBER));
        textEmpName.setText(extras.getString(BPRContract.BPR.COLUMN_EMP_NAME));
        textEmpJob.setText(extras.getString(BPRContract.BPR.COLUMN_EMP_JOB));
    }
}
