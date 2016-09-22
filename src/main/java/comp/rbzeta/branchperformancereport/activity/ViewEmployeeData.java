package comp.rbzeta.branchperformancereport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.handler.BprDBHandler;

public class ViewEmployeeData extends AppCompatActivity {

    private TextView textBranchCode, textBranchName, textPersonalNumber, textEmpName,textEmpJob;
    HashMap<String,String> empMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_data);

        Intent intent = getIntent();
        empMap = (HashMap<String,String>)intent.getSerializableExtra(
                MainScreenActivity.MSG_SAVED_USER);

        textBranchCode = (TextView)findViewById(R.id.textBrancCode);
        textBranchName = (TextView)findViewById(R.id.textBranchName);
        textPersonalNumber = (TextView)findViewById(R.id.textPersonalNumber);
        textEmpName = (TextView)findViewById(R.id.textEmpName);
        textEmpJob = (TextView)findViewById(R.id.textEmpJob);

        setTextView();
    }

    private void setTextView() {
        textBranchCode.setText(empMap.get(BPRContract.BPR.COLUMN_BRANCH_CODE));
        textBranchName.setText(empMap.get(BPRContract.BPR.COLUMN_BRANCH_NAME));
        textPersonalNumber.setText(empMap.get(BPRContract.BPR.COLUMN_PERSONAL_NUMBER));
        textEmpName.setText(empMap.get(BPRContract.BPR.COLUMN_EMP_NAME));
        textEmpJob.setText(empMap.get(BPRContract.BPR.COLUMN_EMP_JOB));
    }

    public void onClickBtnYes(View view){
        Intent intent = new Intent(this,DataQuestionaireActivity.class);
        intent.putExtra(DataEmployeeActivity.KEY_LIST_MAIN_MSG,empMap);
        startActivity(intent);
        finish();
    }

    public void onClickBtnNo(View view){
        BprDBHandler myHandler = new  BprDBHandler(getBaseContext());
        myHandler.deleteSavedEmployeeData();
        Intent intent = new Intent(this,DataEmployeeActivity.class);
        startActivity(intent);
        finish();
    }
}
