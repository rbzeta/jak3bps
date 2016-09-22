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

public class DetailQuestionaireFragment extends Fragment {

    private TextView textBrinetTime, textBrinetMenu, textLasTime, textLasMenu, textSSOTime,
            textSSOMenu, textOtherTime, textOtherMenu;
    private final String DETIK = "  Dtk";

    public DetailQuestionaireFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_questionaire, container,false);
        textBrinetTime = (TextView)view.findViewById(R.id.textBrinetTime);
        textBrinetMenu = (TextView)view.findViewById(R.id.textBrinetMenu);
        textLasTime = (TextView)view.findViewById(R.id.textLasTime);
        textLasMenu = (TextView)view.findViewById(R.id.textLasMenu);
        textSSOTime = (TextView)view.findViewById(R.id.textSSOTime);
        textSSOMenu = (TextView)view.findViewById(R.id.textSSOMenu);
        textOtherTime = (TextView)view.findViewById(R.id.textOtherTime);
        textOtherMenu = (TextView)view.findViewById(R.id.textOtherMenu);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle extras = getActivity().getIntent().getExtras();
        String strBrinetTime = extras.getString(BPRContract.BPR.COLUMN_BRINET_TIME) + DETIK;
        String strLasTime = extras.getString(BPRContract.BPR.COLUMN_LAS_TIME) + DETIK;
        String strSSOTime = extras.getString(BPRContract.BPR.COLUMN_SSO_TIME) + DETIK;
        String strOtherTime = extras.getString(BPRContract.BPR.COLUMN_OTHER_TIME) + DETIK;
        textBrinetTime.setText(strBrinetTime);
        textBrinetMenu.setText(extras.getString(BPRContract.BPR.COLUMN_BRINET_MENU));
        textLasTime.setText(strLasTime);
        textLasMenu.setText(extras.getString(BPRContract.BPR.COLUMN_LAS_MENU));
        textSSOTime.setText(strSSOTime);
        textSSOMenu.setText(extras.getString(BPRContract.BPR.COLUMN_SSO_MENU));
        textOtherTime.setText(strOtherTime);
        textOtherMenu.setText(extras.getString(BPRContract.BPR.COLUMN_OTHER_MENU));

    }
}
