package comp.rbzeta.branchperformancereport.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import comp.rbzeta.branchperformancereport.R;

public class QuestionerActivity extends AppCompatActivity {
    private HashMap<String,String> map;
    private EditText editBrinetLoad;
    private EditText editLasLoad;
    private EditText editSSOLoad;
    private EditText editTimeout;
    private EditText editOffline;
    private EditText editAppKendala;
    private RadioGroup radioGroupBrinets;
    private RadioGroup radioGroupLAS;
    private RadioGroup radioGroupSSO;
    private RadioGroup radioGroupJarkom;
    private RadioGroup radioGroupAppKendala;
    private boolean isApplikasiKendala;

    public static final String KEY_LIST_QUESTION_MSG = "comp.rbzeta.branchperformancereport.KEY_LIST_QUESTION_MSG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioner);

        getSupportActionBar().setTitle("Branch Performance Report");
        getSupportActionBar().setSubtitle("Pertanyaan");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Intent intent = getIntent();
        map = (HashMap<String,String>)intent.getSerializableExtra(MainActivity.KEY_LIST_MAIN_MSG);

        editBrinetLoad = (EditText)findViewById(R.id.editBrinet);
        editLasLoad = (EditText)findViewById(R.id.editLAS);
        editSSOLoad = (EditText)findViewById(R.id.editSSO);
        editTimeout = (EditText)findViewById(R.id.editTimeout);
        editOffline = (EditText)findViewById(R.id.editOffline);
        editAppKendala = (EditText)findViewById(R.id.editAppKendala);
        radioGroupBrinets = (RadioGroup)findViewById(R.id.radioGroupBrinets);
        radioGroupLAS = (RadioGroup)findViewById(R.id.radioGroupLAS);
        radioGroupSSO = (RadioGroup)findViewById(R.id.radioGroupSSO);
        radioGroupJarkom = (RadioGroup)findViewById(R.id.radioGroupJarkom);
        radioGroupAppKendala = (RadioGroup)findViewById(R.id.radioGroupOtherApp);

    }

    public void onClickNextDetail(View view){

        if(validateAllAnswer()){

            map.put("loading_brinet",editBrinetLoad.getText().toString().trim());
            map.put("loading_las",editLasLoad.getText().toString().trim());
            map.put("loading_sso",editSSOLoad.getText().toString().trim());
            map.put("jarkom_timeout",editTimeout.getText().toString().trim());
            map.put("jarkom_offline",editOffline.getText().toString().trim());
            map.put("aplikasi_kendala",editAppKendala.getText().toString().trim());

            int radioCheckedIdBrinets = radioGroupBrinets.getCheckedRadioButtonId();
            int radioCheckedIdLAS = radioGroupLAS.getCheckedRadioButtonId();
            int radioCheckedIdSSO = radioGroupSSO.getCheckedRadioButtonId();
            int radioCheckedIdJarkom = radioGroupJarkom.getCheckedRadioButtonId();
            int radioCheckedIdAppKendala = radioGroupAppKendala.getCheckedRadioButtonId();

            if (radioCheckedIdBrinets >= 0){
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedIdBrinets);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_brinets",radioText);
            }

            if (radioCheckedIdLAS >= 0){
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedIdLAS);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_las",radioText);
            }

            if (radioCheckedIdSSO >= 0){
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedIdSSO);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_sso",radioText);
            }

            if (radioCheckedIdJarkom >= 0){
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedIdJarkom);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_jarkom",radioText);
            }

            if (radioCheckedIdAppKendala >= 0){
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedIdAppKendala);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_appkendala",radioText);
            }

            Intent intent = new Intent(this,DetailAnswerActivity.class);
            intent.putExtra(KEY_LIST_QUESTION_MSG,map);
            startActivity(intent);

        }else Toast.makeText(QuestionerActivity.this, "Harap isi semua jawaban", Toast.LENGTH_SHORT).show();


    }

    public boolean validateAllAnswer (){

        //Use for iterating layout objects

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearScroll2);
        for (int i = 0; i < linearLayout.getChildCount() ; i++) {
            View v = linearLayout.getChildAt(i);
            if(v instanceof EditText){

                String text = ((EditText) v).getText().toString().trim();

                if(v.getId() != R.id.editAppKendala){
                    if (text.matches("")) {
                        EditText editText = (EditText)findViewById(v.getId());
                        editText.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                        return  false;
                    }
                }else{
                    if (isApplikasiKendala){
                        if (text.matches("")) {
                            EditText editText = (EditText)findViewById(v.getId());
                            editText.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                            return  false;
                        }
                    }
                }

            }else if (v instanceof RadioGroup){

                if(v.getId() == R.id.radioGroupOtherApp){
                    RadioButton radioBtnSelected = (RadioButton)findViewById(((RadioGroup) v).getCheckedRadioButtonId());
                    String radioText = radioBtnSelected.getText().toString();
                    if (radioText.matches("Yes")) {
                        isApplikasiKendala = true;
                    }
                }

            }
        }

        return true;
    }
}
