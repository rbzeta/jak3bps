package comp.rbzeta.branchperformancereport.activity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.dialog.SaveDialogFragment;
import comp.rbzeta.branchperformancereport.handler.BprDBHandler;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;
import comp.rbzeta.branchperformancereport.model.ResponseMessage;
import comp.rbzeta.branchperformancereport.model.ResponseMessageResponse;
import comp.rbzeta.branchperformancereport.rest.ApiClient;
import comp.rbzeta.branchperformancereport.rest.ApiInterface;
import comp.rbzeta.branchperformancereport.util.UIHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataNetworkActivity extends AppCompatActivity {

    private HashMap<String, String> map;
    private EditText inputTimeout, inputOffline;
    private TextInputLayout inputLytTimeout, inputLytOffline;
    private RadioGroup radioGroupNetwork;

    private Button btnNext;

    public static final String KEY_LIST_MAIN_MSG =
            "comp.rbzeta.branchperformancereport.activity.DataNetworkActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_network);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarNetwork);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getString(R.string.txt_subtitle_questions_net));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputLytTimeout = (TextInputLayout)findViewById(R.id.input_layout_timeout);
        inputLytOffline = (TextInputLayout)findViewById(R.id.input_layout_offline);
        inputTimeout = (EditText)findViewById(R.id.input_timeout);
        inputOffline = (EditText)findViewById(R.id.input_offline);
        radioGroupNetwork = (RadioGroup)findViewById(R.id.radio_group_network);
        btnNext = (Button)findViewById(R.id.btn_next_net);

        inputTimeout.addTextChangedListener(new MyTextWatcher(inputTimeout));
        inputOffline.addTextChangedListener(new MyTextWatcher(inputOffline));

        Intent intent = getIntent();
        map = (HashMap<String,String>)intent
                .getSerializableExtra(DataQuestionaireActivity.KEY_LIST_MAIN_MSG);

    }

    public void onClickBtnNext(View view){
        submitForm();

    }

    private void submitForm() {
        if (!validateTimeout()) return;
        if (!validateOffline()) return;

        map.put(BPRContract.BPR.COLUMN_NET_TIMEOUT,inputTimeout.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_NET_OFFLINE,inputOffline.getText().toString().trim());

        int checkedRadioNetworkId = radioGroupNetwork.getCheckedRadioButtonId();
        if(checkedRadioNetworkId >= 0){

            RadioButton radioBtnSelected = (RadioButton)findViewById(checkedRadioNetworkId);

            map.put(BPRContract.BPR.COLUMN_NET_DEVICE,radioBtnSelected.getText().toString().trim());
        }

        /*
        Intent intent = new Intent(this,DetailAnswerActivity.class);
        intent.putExtra(KEY_LIST_MAIN_MSG,map);
        startActivity(intent);
        */
        saveAction();
    }

    private void saveAction() {
        showSaveDialog();
    }

    void showSaveDialog() {
        DialogFragment dialog = new SaveDialogFragment();
        dialog.show(getFragmentManager(),"saveDialog");
    }

    public void doPositiveClick() {
        final ProgressDialog progress= ProgressDialog.show(this,getString(R.string.txt_title_loading),
                getString(R.string.txt_dialog_save),false,false);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        final BranchPerformanceModel bpr = new BranchPerformanceModel();
        setBrancPermormanceModel(bpr);

        Call<ResponseMessageResponse> call = apiService.saveBranchPerformanceReport(bpr);
        call.enqueue(new Callback<ResponseMessageResponse>() {
            @Override
            public void onResponse(Call<ResponseMessageResponse> call, Response<ResponseMessageResponse> response) {
                progress.dismiss();
                ResponseMessage msg = response.body().getResponseMessage();

                UIHelper.showToastLong(DataNetworkActivity.this,
                        (null != msg)?msg.getMessage():getString(R.string.msg_onfailure_callback));

                Log.d("REQUEST MESSAGE : ",msg.getMessage());

                if (msg != null) {
                    if (msg.getCode() == 1) {

                        saveUserData(bpr);
                        Intent intent =
                                new Intent(DataNetworkActivity.this,MainScreenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseMessageResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DataNetworkActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void saveUserData(BranchPerformanceModel bpr) {
        BprDBHandler myHandler = new BprDBHandler(getBaseContext());

        myHandler.saveEmployeeData(bpr);

    }

    private void setBrancPermormanceModel(BranchPerformanceModel bpr) {
        bpr.setBranchCode(map.get(BPRContract.BPR.COLUMN_BRANCH_CODE));
        bpr.setBranchName(map.get(BPRContract.BPR.COLUMN_BRANCH_NAME));
        bpr.setPersonalNumber(map.get(BPRContract.BPR.COLUMN_PERSONAL_NUMBER));
        bpr.setEmpName(map.get(BPRContract.BPR.COLUMN_EMP_NAME));
        bpr.setEmpJob(map.get(BPRContract.BPR.COLUMN_EMP_JOB));
        bpr.setBrinetTime(map.get(BPRContract.BPR.COLUMN_BRINET_TIME));
        bpr.setBrinetMenu(map.get(BPRContract.BPR.COLUMN_BRINET_MENU));
        bpr.setLasTime(map.get(BPRContract.BPR.COLUMN_LAS_TIME));
        bpr.setLasMenu(map.get(BPRContract.BPR.COLUMN_LAS_MENU));
        bpr.setSsoTime(map.get(BPRContract.BPR.COLUMN_SSO_TIME));
        bpr.setSsoMenu(map.get(BPRContract.BPR.COLUMN_SSO_MENU));
        bpr.setOtherTime(map.get(BPRContract.BPR.COLUMN_OTHER_TIME));
        bpr.setOtherMenu(map.get(BPRContract.BPR.COLUMN_OTHER_MENU));
        bpr.setNetworkTimeout(map.get(BPRContract.BPR.COLUMN_NET_TIMEOUT));
        bpr.setNetworkOffline(map.get(BPRContract.BPR.COLUMN_NET_OFFLINE));
        bpr.setNetworkDevice(map.get(BPRContract.BPR.COLUMN_NET_DEVICE));
    }

    private boolean validateTimeout() {
        if (inputTimeout.getText().toString().trim().isEmpty()) {
            inputTimeout.setError(getString(R.string.err_msg_network_timeout));
            UIHelper.requestFocus(getWindow(),inputTimeout);
            return false;
        } else {
            inputLytTimeout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateOffline() {
        if (inputOffline.getText().toString().trim().isEmpty()) {
            inputOffline.setError(getString(R.string.err_msg_network_offline));
            UIHelper.requestFocus(getWindow(),inputOffline);
            return false;
        } else {
            inputLytOffline.setErrorEnabled(false);
        }

        return true;
    }


    private class MyTextWatcher implements TextWatcher {

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
                case R.id.input_timeout:
                    validateTimeout();
                    break;
                case R.id.input_offline:
                    validateOffline();
                    break;
            }
        }
    }
}
