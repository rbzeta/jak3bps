package comp.rbzeta.branchperformancereport.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.util.UIHelper;

public class DataEmployeeActivity extends AppCompatActivity {

    private EditText inputBranchCode, inputBranchName, inputPersonalNumber,
            inputEmpName, inputEmpJob;
    private TextInputLayout inputLytBranchCode, inputLytBranchName, inputLytPersonalNumber,
            inputLytEmpName, inputLytEmpJob;
    private Button btnNext;

    public static final String KEY_LIST_MAIN_MSG =
            "comp.rbzeta.branchperformancereport.activity.DataEmployeeActivity.KEY_LIST_MAIN_MSG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_employee);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarEmployee);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getString(R.string.txt_subtitle_emp_data));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputLytBranchCode = (TextInputLayout)findViewById(R.id.input_layout_branch_code);
        inputLytBranchName = (TextInputLayout)findViewById(R.id.input_layout_branch_name);
        inputLytPersonalNumber = (TextInputLayout)findViewById(R.id.input_layout_personal_number);
        inputLytEmpName = (TextInputLayout)findViewById(R.id.input_layout_emp_name);
        inputLytEmpJob = (TextInputLayout)findViewById(R.id.input_layout_emp_job);
        inputBranchCode = (EditText)findViewById(R.id.input_branch_code);
        inputBranchName = (EditText)findViewById(R.id.input_branch_name);
        inputPersonalNumber = (EditText)findViewById(R.id.input_personal_number);
        inputEmpName = (EditText)findViewById(R.id.input_emp_name);
        inputEmpJob = (EditText)findViewById(R.id.input_emp_job);
        btnNext = (Button)findViewById(R.id.btn_next);

        inputBranchCode.addTextChangedListener(new MyTextWatcher(inputBranchCode));
        inputBranchName.addTextChangedListener(new MyTextWatcher(inputBranchName));
        inputPersonalNumber.addTextChangedListener(new MyTextWatcher(inputPersonalNumber));
        inputEmpName.addTextChangedListener(new MyTextWatcher(inputEmpName));
        inputEmpJob.addTextChangedListener(new MyTextWatcher(inputEmpJob));


    }

    public void onClickBtnNext(View view){
        submitForm();
        
    }

    private void submitForm() {
        if (!validateBranchCode()) return;
        if (!validateBranchName()) return;
        if (!validatePersonalNumber()) return;
        if (!validateEmpName()) return;
        if (!validateEmpJob()) return;

        HashMap<String,String> map = new HashMap<>();

        map.put(BPRContract.BPR.COLUMN_BRANCH_CODE,inputBranchCode.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_BRANCH_NAME,inputBranchName.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_PERSONAL_NUMBER,inputPersonalNumber.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_EMP_NAME,inputEmpName.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_EMP_JOB,inputEmpJob.getText().toString().trim());

        Intent intent = new Intent(this,DataQuestionaireActivity.class);
        intent.putExtra(KEY_LIST_MAIN_MSG,map);
        startActivity(intent);
    }

    private boolean validateBranchCode() {
        if (inputBranchCode.getText().toString().trim().isEmpty()) {
            inputBranchCode.setError(getString(R.string.err_msg_branch_code));
            UIHelper.requestFocus(getWindow(),inputBranchCode);
            return false;
        } else {
            inputLytBranchCode.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBranchName() {
        if (inputBranchName.getText().toString().trim().isEmpty()) {
            inputBranchName.setError(getString(R.string.err_msg_branch_name));
            UIHelper.requestFocus(getWindow(),inputBranchName);
            return false;
        } else {
            inputLytBranchName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePersonalNumber() {
        if (inputPersonalNumber.getText().toString().trim().isEmpty()) {
            inputPersonalNumber.setError(getString(R.string.err_msg_personal_number));
            UIHelper.requestFocus(getWindow(),inputPersonalNumber);
            return false;
        } else {
            inputLytPersonalNumber.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmpName() {
        if (inputEmpName.getText().toString().trim().isEmpty()) {
            inputEmpName.setError(getString(R.string.err_msg_emp_name));
            UIHelper.requestFocus(getWindow(),inputEmpName);
            return false;
        } else {
            inputLytEmpName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmpJob() {
        if (inputEmpJob.getText().toString().trim().isEmpty()) {
            inputEmpJob.setError(getString(R.string.err_msg_emp_job));
            UIHelper.requestFocus(getWindow(),inputEmpJob);
            return false;
        } else {
            inputLytEmpJob.setErrorEnabled(false);
        }

        return true;
    }

    private class MyTextWatcher implements TextWatcher{

        private View view;

        private MyTextWatcher(View view){
            this.view = view;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.input_branch_code:
                    validateBranchCode();
                    break;
                case R.id.input_branch_name:
                    validateBranchName();
                    break;
                case R.id.input_personal_number:
                    validatePersonalNumber();
                    break;
                case R.id.input_emp_name:
                    validateEmpName();
                    break;
                case R.id.input_emp_job:
                    validateEmpJob();
                    break;
            }
        }
    }
}
