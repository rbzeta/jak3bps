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

public class MainActivity extends AppCompatActivity {
    private EditText editKodeUker ;
    private EditText editNamaUker ;
    private EditText editPNPekerja ;
    private EditText editNamaPekerja ;
    private RadioGroup radioGroupPekerjaan;

    public static final String KEY_LIST_MAIN_MSG = "comp.rbzeta.branchperformancereport.KEY_LIST_MAIN_MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Branch Performance Report");
        getSupportActionBar().setSubtitle("Data Pekerja");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        editKodeUker = (EditText)findViewById(R.id.editKodeUker);
        editNamaUker = (EditText)findViewById(R.id.editNamaUker);
        editPNPekerja = (EditText)findViewById(R.id.editPN);
        editNamaPekerja = (EditText)findViewById(R.id.editNamaPekerja);
        radioGroupPekerjaan = (RadioGroup)findViewById(R.id.radioGroupPekerjaan);


    }

    public boolean validateAllAnswer (){

        //Use for iterating layout objects

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearScroll);
        for (int i = 0; i < linearLayout.getChildCount() ; i++) {
            View v = linearLayout.getChildAt(i);
            if(v instanceof EditText){
                String text = ((EditText) v).getText().toString().trim();

                if (text.matches("")) {
                    EditText editText = (EditText)findViewById(v.getId());
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                    return  false;
                }
            }
        }

        return true;
    }
    public void onClickBtnNext(View view){

        //ArrayList<HashMap<String,String>> mapList = new ArrayList<>();
        if(validateAllAnswer()){
            HashMap<String,String> map = new HashMap<>();

            map.put("kode_uker",editKodeUker.getText().toString().trim());
            map.put("nama_uker",editNamaUker.getText().toString().trim());
            map.put("pn_pekerja",editPNPekerja.getText().toString().trim());
            map.put("nama_pekerja",editNamaPekerja.getText().toString().trim());

            int radioCheckedId = radioGroupPekerjaan.getCheckedRadioButtonId();

            if(radioCheckedId == -1){
                //No radio button selected
            }else{
                RadioButton radioBtnSelected = (RadioButton)findViewById(radioCheckedId);
                String radioText = radioBtnSelected.getText().toString();
                map.put("radio_pekerjaan",radioText);
            }

            Intent intent = new Intent(this,QuestionerActivity.class);
            intent.putExtra(KEY_LIST_MAIN_MSG,map);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this, "Harap isi semua jawaban", Toast.LENGTH_SHORT).show();
        }
    }
}
