package comp.rbzeta.branchperformancereport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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

public class DataQuestionaireActivity extends AppCompatActivity {

    private HashMap<String,String> map;
    private EditText inputBrinetTime, inputBrinetMenu, inputLasTime,inputLasMenu,
            inputSSOTime, inputSSOMenu, inputOtherTime, inputOtherMenu;
    private TextInputLayout inputLytBrinetTime, inputLytBrinetMenu, inputLytLasTime,inputLytLasMenu,
            inputLytSSOTime, inputLytSSOMenu, inputLytOtherTime, inputLytOtherMenu;
    private Button btnNext;

    public static final String KEY_LIST_MAIN_MSG =
            "comp.rbzeta.branchperformancereport.activity.DataQuestionaireActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_questionaire);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarQuestionare);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getString(R.string.txt_subtitle_questions_app));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputLytBrinetTime = (TextInputLayout)findViewById(R.id.input_layout_brinet_time);
        inputLytBrinetMenu = (TextInputLayout)findViewById(R.id.input_layout_brinet_menu);
        inputLytLasTime = (TextInputLayout)findViewById(R.id.input_layout_las_time);
        inputLytLasMenu = (TextInputLayout)findViewById(R.id.input_layout_las_menu);
        inputLytSSOTime = (TextInputLayout)findViewById(R.id.input_layout_sso_time);
        inputLytSSOMenu = (TextInputLayout)findViewById(R.id.input_layout_sso_menu);
        inputLytOtherTime = (TextInputLayout)findViewById(R.id.input_layout_other_time);
        inputLytOtherMenu = (TextInputLayout)findViewById(R.id.input_layout_other_menu);
        inputBrinetTime = (EditText)findViewById(R.id.input_brinet_time);
        inputBrinetMenu = (EditText)findViewById(R.id.input_brinet_menu);
        inputLasTime = (EditText)findViewById(R.id.input_las_time);
        inputLasMenu = (EditText)findViewById(R.id.input_las_menu);
        inputSSOTime = (EditText)findViewById(R.id.input_sso_time);
        inputSSOMenu = (EditText)findViewById(R.id.input_sso_menu);
        inputOtherTime = (EditText)findViewById(R.id.input_other_time);
        inputOtherMenu = (EditText)findViewById(R.id.input_other_menu);
        btnNext = (Button)findViewById(R.id.btn_next_app);

        inputBrinetTime.addTextChangedListener(new MyTextWatcher(inputBrinetTime));
        inputBrinetMenu.addTextChangedListener(new MyTextWatcher(inputBrinetMenu));
        inputLasTime.addTextChangedListener(new MyTextWatcher(inputLasTime));
        inputLasMenu.addTextChangedListener(new MyTextWatcher(inputLasMenu));
        inputSSOTime.addTextChangedListener(new MyTextWatcher(inputSSOTime));
        inputSSOMenu.addTextChangedListener(new MyTextWatcher(inputSSOMenu));
        inputOtherTime.addTextChangedListener(new MyTextWatcher(inputOtherTime));
        inputOtherMenu.addTextChangedListener(new MyTextWatcher(inputOtherMenu));

        Intent intent = getIntent();
        map = (HashMap<String,String>)intent.getSerializableExtra(DataEmployeeActivity.KEY_LIST_MAIN_MSG);

    }

    public void onClickBtnNext(View view){
        submitForm();

    }

    private void submitForm() {
        if (!validateBrinetTime()) return;
        if (!validateBrinetMenu()) return;
        if (!validateLasTime()) return;
        if (!validateLasMenu()) return;
        if (!validateSSOTime()) return;
        if (!validateSSOMenu()) return;
        //if (!validateOtherTime()) return;
        //if (!validateOtherMenu()) return;

        map.put(BPRContract.BPR.COLUMN_BRINET_TIME,inputBrinetTime.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_BRINET_MENU,inputBrinetMenu.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_LAS_TIME,inputLasTime.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_LAS_MENU,inputLasMenu.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_SSO_TIME,inputSSOTime.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_SSO_MENU,inputSSOMenu.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_OTHER_TIME,inputOtherTime.getText().toString().trim());
        map.put(BPRContract.BPR.COLUMN_OTHER_MENU,inputOtherMenu.getText().toString().trim());


        Intent intent = new Intent(this,DataNetworkActivity.class);
        intent.putExtra(KEY_LIST_MAIN_MSG,map);
        startActivity(intent);
    }

    private boolean validateBrinetTime() {
        if (inputBrinetTime.getText().toString().trim().isEmpty()) {
            inputBrinetTime.setError(getString(R.string.err_msg_brinet_time));
            UIHelper.requestFocus(getWindow(),inputBrinetTime);
            return false;
        } else {
            inputLytBrinetTime.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBrinetMenu() {
        if (inputBrinetMenu.getText().toString().trim().isEmpty()) {
            inputBrinetMenu.setError(getString(R.string.err_msg_brinet_menu));
            UIHelper.requestFocus(getWindow(),inputBrinetMenu);
            return false;
        } else {
            inputLytBrinetMenu.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLasTime() {
        if (inputLasTime.getText().toString().trim().isEmpty()) {
            inputLasTime.setError(getString(R.string.err_msg_las_time));
            UIHelper.requestFocus(getWindow(),inputLasTime);
            return false;
        } else {
            inputLytLasTime.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLasMenu() {
        if (inputLasMenu.getText().toString().trim().isEmpty()) {
            inputLasMenu.setError(getString(R.string.err_msg_las_menu));
            UIHelper.requestFocus(getWindow(),inputLasMenu);
            return false;
        } else {
            inputLytLasMenu.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSSOTime() {
        if (inputSSOTime.getText().toString().trim().isEmpty()) {
            inputSSOTime.setError(getString(R.string.err_msg_sso_time));
            UIHelper.requestFocus(getWindow(),inputSSOTime);
            return false;
        } else {
            inputLytSSOTime.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSSOMenu() {
        if (inputSSOMenu.getText().toString().trim().isEmpty()) {
            inputSSOMenu.setError(getString(R.string.err_msg_sso_menu));
            UIHelper.requestFocus(getWindow(),inputSSOMenu);
            return false;
        } else {
            inputLytSSOMenu.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateOtherTime() {
        if (inputOtherTime.getText().toString().trim().isEmpty()) {
            inputOtherTime.setError(getString(R.string.err_msg_other_time));
            UIHelper.requestFocus(getWindow(),inputOtherTime);
            return false;
        } else {
            inputLytOtherTime.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateOtherMenu() {
        if (inputOtherMenu.getText().toString().trim().isEmpty()) {
            inputOtherMenu.setError(getString(R.string.err_msg_other_menu));
            UIHelper.requestFocus(getWindow(),inputOtherMenu);
            return false;
        } else {
            inputLytOtherMenu.setErrorEnabled(false);
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
                case R.id.input_brinet_time:
                    validateBrinetTime();
                    break;
                case R.id.input_brinet_menu:
                    validateBrinetMenu();
                    break;
                case R.id.input_las_time:
                    validateLasTime();
                    break;
                case R.id.input_las_menu:
                    validateLasMenu();
                    break;
                case R.id.input_sso_time:
                    validateSSOTime();
                    break;
                case R.id.input_sso_menu:
                    validateSSOMenu();
                    break;
                case R.id.input_other_time:
                    validateOtherTime();
                    break;
                case R.id.input_other_menu:
                    validateOtherMenu();
                    break;
            }
        }
    }

}
