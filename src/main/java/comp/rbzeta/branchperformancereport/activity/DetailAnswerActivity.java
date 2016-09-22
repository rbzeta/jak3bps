package comp.rbzeta.branchperformancereport.activity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.List;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.dialog.SaveDialogFragment;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModelResponse;
import comp.rbzeta.branchperformancereport.model.ResponseMessage;
import comp.rbzeta.branchperformancereport.model.ResponseMessageResponse;
import comp.rbzeta.branchperformancereport.rest.ApiClient;
import comp.rbzeta.branchperformancereport.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAnswerActivity extends AppCompatActivity {
    private HashMap<String, String> map;
    private LinearLayout linearLayout;

    private TextView editKodeUker;
    private TextView editNamaUker;
    private TextView editPNPekerja;
    private TextView editNamaPekerja;
    private TextView editGroupPekerjaan;
    private TextView editBrinetLoad;
    private TextView editLasLoad;
    private TextView editSSOLoad;
    private TextView editTimeout;
    private TextView editOffline;
    private TextView editAppKendala;
    private TextView radioGroupBrinets;
    private TextView radioGroupLAS;
    private TextView radioGroupSSO;
    private TextView radioGroupJarkom;
    private TextView radioGroupAppKendala;
    private Button btnKonfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_answer);

        getSupportActionBar().setTitle("Branch Performance Report");
        getSupportActionBar().setSubtitle("Review Jawaban");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        map = (HashMap<String, String>) getIntent().getSerializableExtra(QuestionerActivity.KEY_LIST_QUESTION_MSG);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutDetail);

        setDetailLayout();
        btnKonfirmasi.setOnClickListener(new View.OnClickListener(){
                                             @Override
                                             public void onClick(View view){
                                                showSaveDialog();

                                             }

                                         }
        );



        /*
        //Looping through Hasmap
        for (HashMap.Entry<String,String> mapItem : map.entrySet() ) {
            String kodeUker = map.get("kode_uker");
        }*/
    }

    void showSaveDialog() {
        DialogFragment dialog = new SaveDialogFragment();
        dialog.show(getFragmentManager(),"saveDialog");
    }

    public void doPositiveClick() {
        final ProgressDialog progress= ProgressDialog.show(this,"Loading..","Please wait",false,false);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        BranchPerformanceModel bpr = new BranchPerformanceModel();
        setBrancPermormanceModel(bpr);

        Call<ResponseMessageResponse> call = apiService.saveBranchPerformanceReport(bpr);
        call.enqueue(new Callback<ResponseMessageResponse>() {
            @Override
            public void onResponse(Call<ResponseMessageResponse> call, Response<ResponseMessageResponse> response) {
                progress.dismiss();
                ResponseMessage msg = response.body().getResponseMessage();

                Toast.makeText(DetailAnswerActivity.this,
                        (null != msg)?msg.getMessage():"Failed to get response message.",
                        Toast.LENGTH_LONG).show();
                Log.d("REQUEST MESSAGE : ",msg.getMessage());

                if (msg != null) {
                    if (msg.getCode() > 0) {
                        Intent intent =
                                new Intent(DetailAnswerActivity.this,MainScreenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseMessageResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DetailAnswerActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void setBrancPermormanceModel(BranchPerformanceModel bpr) {
        /*bpr.setKodeUker(map.get("kode_uker"));
        bpr.setNamaUker(map.get("nama_uker"));
        bpr.setPnPekerja(map.get("pn_pekerja"));
        bpr.setNamaPekerja(map.get("nama_pekerja"));
        bpr.setJabatanPekerja(map.get("radio_pekerjaan"));
        bpr.setBrinetTime(map.get("loading_brinet"));
        bpr.setBrinetMenu(map.get("radio_brinets"));
        bpr.setLasTime(map.get("loading_las"));
        bpr.setLasMenu(map.get("radio_las"));
        bpr.setSsoTime(map.get("loading_sso"));
        bpr.setSsoMenu(map.get("radio_sso"));
        bpr.setJarkomTimeout(map.get("jarkom_timeout"));
        bpr.setJarkomOffline(map.get("jarkom_offline"));
        bpr.setJarkomUker(map.get("radio_jarkom"));
        bpr.setAplikasiKendala(map.get("aplikasi_kendala"));
        bpr.setIsKendala(map.get("radio_appkendala"));*/
    }

    public void doNegativeClick() {

    }

    public void setDetailLayout() {

        TextView qKodeUker = new TextView(this);
        qKodeUker.setText(R.string.question_kode_uker);
        qKodeUker.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qKodeUkerParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qKodeUkerParams.setMargins(0,5,0,10);
        qKodeUker.setLayoutParams(qKodeUkerParams);

        linearLayout.addView(qKodeUker);
        editKodeUker = new TextView(this);
        editKodeUker.setText("Jawaban : "+map.get("kode_uker"));
        editKodeUker.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editKodeUker);

        TextView qNamaUker = new TextView(this);
        qNamaUker.setText(R.string.question_nama_uker);
        qNamaUker.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qNamaUkerParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qNamaUkerParams.setMargins(0,20,0,0);
        qNamaUker.setLayoutParams(qNamaUkerParams);
        linearLayout.addView(qNamaUker);
        editNamaUker = new TextView(this);
        editNamaUker.setText("Jawaban : "+map.get("nama_uker"));
        editNamaUker.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editNamaUker);

        TextView qPNPekerja = new TextView(this);
        qPNPekerja.setText(R.string.question_pn_pekerja);
        qPNPekerja.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams qPNPekerjaParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qPNPekerjaParams.setMargins(0,20,0,0);
        qPNPekerja.setLayoutParams(qPNPekerjaParams);
        linearLayout.addView(qPNPekerja);
        editPNPekerja = new TextView(this);
        editPNPekerja.setText("Jawaban : "+map.get("pn_pekerja"));
        editPNPekerja.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editPNPekerja);

        TextView qNamaPekerja = new TextView(this);
        qNamaPekerja.setText(R.string.question_nama_pekerja);
        qNamaPekerja.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams qNamaPekerjaParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qNamaPekerjaParams.setMargins(0,20,0,0);
        qNamaPekerja.setLayoutParams(qNamaPekerjaParams);
        linearLayout.addView(qNamaPekerja);
        editNamaPekerja = new TextView(this);
        editNamaPekerja.setText("Jawaban : "+map.get("nama_pekerja"));
        editNamaPekerja.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editNamaPekerja);

        TextView qPekerjaan = new TextView(this);
        qPekerjaan.setText(R.string.question_pekerjaan);
        qPekerjaan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams qPekerjaanParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qPekerjaanParams.setMargins(0,20,0,0);
        qPekerjaan.setLayoutParams(qPekerjaanParams);
        linearLayout.addView(qPekerjaan);
        editGroupPekerjaan = new TextView(this);
        editGroupPekerjaan.setText("Jawaban : "+map.get("radio_pekerjaan"));
        editGroupPekerjaan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editGroupPekerjaan);

        TextView qBrinets = new TextView(this);
        qBrinets.setText(R.string.question_brinets);
        qBrinets.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams qBrinetsParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qBrinetsParams.setMargins(0,20,0,0);
        qBrinets.setLayoutParams(qBrinetsParams);
        linearLayout.addView(qBrinets);
        editBrinetLoad = new TextView(this);
        editBrinetLoad.setText("Jawaban : "+map.get("loading_brinet"));
        editBrinetLoad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editBrinetLoad);

        TextView qBrinetsMenu = new TextView(this);
        qBrinetsMenu.setText(R.string.question_brinets_load);
        qBrinetsMenu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams qBrinetsMenuParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qBrinetsMenuParams.setMargins(0,20,0,0);
        qBrinetsMenu.setLayoutParams(qBrinetsMenuParams);
        linearLayout.addView(qBrinetsMenu);
        radioGroupBrinets = new TextView(this);
        radioGroupBrinets.setText("Jawaban : "+map.get("radio_brinets"));
        radioGroupBrinets.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroupBrinets);

        TextView qLas = new TextView(this);
        qLas.setText(R.string.question_las);
        qLas.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qLasParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qLasParams.setMargins(0,20,0,0);
        qLas.setLayoutParams(qLasParams);
        linearLayout.addView(qLas);
        editLasLoad = new TextView(this);
        editLasLoad.setText("Jawaban : "+map.get("loading_las"));
        editLasLoad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editLasLoad);

        TextView qLasMenu = new TextView(this);
        qLasMenu.setText(R.string.question_sso_load);
        qLasMenu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qLasMenuParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qLasMenuParams.setMargins(0,20,0,0);
        qLasMenu.setLayoutParams(qLasMenuParams);
        linearLayout.addView(qLasMenu);
        radioGroupLAS = new TextView(this);
        radioGroupLAS.setText("Jawaban : "+map.get("radio_las"));
        radioGroupLAS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroupLAS);

        TextView qSSO = new TextView(this);
        qSSO.setText(R.string.question_sso);
        qSSO.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qSSOParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qSSOParams.setMargins(0,20,0,0);
        qSSO.setLayoutParams(qSSOParams);
        linearLayout.addView(qSSO);
        editSSOLoad = new TextView(this);
        editSSOLoad.setText("Jawaban : "+map.get("loading_sso"));
        editSSOLoad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editSSOLoad);

        TextView qSSOMenu = new TextView(this);
        qSSOMenu.setText(R.string.question_sso_load);
        qSSOMenu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qSSOMenuParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qSSOMenuParams.setMargins(0,20,0,0);
        qSSOMenu.setLayoutParams(qSSOMenuParams);
        linearLayout.addView(qSSOMenu);
        radioGroupSSO = new TextView(this);
        radioGroupSSO.setText("Jawaban : "+map.get("radio_sso"));
        radioGroupSSO.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroupSSO);


        TextView qTimeout = new TextView(this);
        qTimeout.setText(R.string.question_timeout);
        qTimeout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qTimeoutParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qTimeoutParams.setMargins(0,20,0,0);
        qTimeout.setLayoutParams(qTimeoutParams);
        linearLayout.addView(qTimeout);
        editTimeout = new TextView(this);
        editTimeout.setText("Jawaban : "+map.get("jarkom_timeout"));
        editTimeout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editTimeout);

        TextView qOffline = new TextView(this);
        qOffline.setText(R.string.question_offline);
        qOffline.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qOfflineParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qOfflineParams.setMargins(0,20,0,0);
        qOffline.setLayoutParams(qOfflineParams);
        linearLayout.addView(qOffline);
        editOffline = new TextView(this);
        editOffline.setText("Jawaban : "+map.get("jarkom_offline"));
        editOffline.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editOffline);

        TextView qJarkom = new TextView(this);
        qJarkom.setText(R.string.question_jarkom);
        qJarkom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qJarkomParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qJarkomParams.setMargins(0,1,0,0);
        qJarkom.setLayoutParams(qJarkomParams);
        linearLayout.addView(qJarkom);
        radioGroupJarkom = new TextView(this);
        radioGroupJarkom.setText("Jawaban : "+map.get("radio_jarkom"));
        radioGroupJarkom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroupJarkom);

        TextView qKendala = new TextView(this);
        qKendala.setText(R.string.question_appkendala);
        qKendala.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qKendalaParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qKendalaParams.setMargins(0,20,0,0);
        qKendala.setLayoutParams(qKendalaParams);
        linearLayout.addView(qKendala);
        radioGroupAppKendala = new TextView(this);
        radioGroupAppKendala.setText("Jawaban : "+map.get("radio_appkendala"));
        radioGroupAppKendala.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroupAppKendala);

        TextView qAppKendala = new TextView(this);
        qAppKendala.setText(R.string.question_kendalanya);
        qAppKendala.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams qAppKendalaParams = (LinearLayout.LayoutParams)qKodeUker.getLayoutParams();
        qAppKendalaParams.setMargins(0,20,0,0);
        qAppKendala.setLayoutParams(qAppKendalaParams);
        linearLayout.addView(qAppKendala);
        editAppKendala = new TextView(this);
        editAppKendala.setText("Jawaban : "+map.get("aplikasi_kendala"));
        editAppKendala.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(editAppKendala);

        btnKonfirmasi = new Button(this);
        btnKonfirmasi.setText(R.string.btn_simpan);
        btnKonfirmasi.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        btnKonfirmasi.setTextColor(getResources().getColor(R.color.white));
        btnKonfirmasi.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(btnKonfirmasi);

    }

}
