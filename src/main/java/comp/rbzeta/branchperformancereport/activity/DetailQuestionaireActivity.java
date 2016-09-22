package comp.rbzeta.branchperformancereport.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.adapter.ViewPagerAdapter;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.fragment.DetailEmployeeFragment;
import comp.rbzeta.branchperformancereport.fragment.DetailNetworkFragment;
import comp.rbzeta.branchperformancereport.fragment.DetailQuestionaireFragment;

public class DetailQuestionaireActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int bprId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_questionaire);

        toolbar = (Toolbar) findViewById(R.id.toolbarDetailEmp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.toolbar_detail_title));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Bundle extras = getIntent().getExtras();
        bprId = extras.getInt(BPRContract.BPR._ID);

        /*tabLayout.addTab(tabLayout.newTab().setText("Tab One"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab Three"));*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DetailEmployeeFragment(),
                getString(R.string.title_fragment_detail_emp));
        adapter.addFragment(new DetailQuestionaireFragment(),
                getString(R.string.title_fragment_detail_question));
        adapter.addFragment(new DetailNetworkFragment(),
                getString(R.string.title_fragment_detail_network));
        /*adapter.addFragment(new HideToolbarFragment(),"TES");*/
        viewPager.setAdapter(adapter);
    }

   /*














        *//*Bundle empBundle = new Bundle();
        empBundle.putString("text_branch_name","TES");
        DetailEmployeeFragment detailEmployeeFragment = new DetailEmployeeFragment();
        detailEmployeeFragment.setArguments(empBundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(detailEmployeeFragment,
                getString(R.string.title_fragment_detail_emp));
        viewPager.setAdapter(adapter);

        empBundle.putString("text_branch_name","WOWWWWWW");
        DetailEmployeeFragment empFragment = new DetailEmployeeFragment();
        empFragment.setArguments(empBundle);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragment_layout_container, empFragment);
        transaction.addToBackStack(null);*//*

        //getBPRModelById(bprId);
    }

    private void getBPRModelById(int bprId) {
        checkConnection();
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();

        if (isConnected) {

            prepareBPRDataOnline();

        } else {
            UIHelper.showCustomSnackBar(findViewById(R.id.coordinatorLayout),
                    getResources().getString(R.string.msg_no_internet), Color.RED);
            prepareBPRDataNotFound();
        }
    }

    private void prepareBPRDataOnline() {
        //final ProgressDialog progress= ProgressDialog.show(this,"Loading..","Please wait",false,false);
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<BranchPerformanceModelResponse> call = apiService.getBPRByID(bprId);
            call.enqueue(new Callback<BranchPerformanceModelResponse>() {
                @Override
                public void onResponse(Call<BranchPerformanceModelResponse> call,
                                       Response<BranchPerformanceModelResponse> response) {
                    //progress.dismiss();

                    BranchPerformanceModel bpr = response.body().getBpr();

                    int responseCode = response.body().getCode();
                    String responseMsg = response.body().getMessage();

                    if (bpr != null) {

                        if (responseCode == 1) {

                            Bundle empBundle = new Bundle();
                            empBundle.putString("text_branch_name",bpr.getBranchName());
                            empBundle.putString("text_personal_number",bpr.getPersonalNumber());
                            empBundle.putString("text_emp_name",bpr.getEmpName());
                            empBundle.putString("text_emp_job",bpr.getEmpJob());
                            Toast.makeText(DetailQuestionaireActivity.this, bpr.getEmpName(), Toast.LENGTH_SHORT).show();
                           *//* DetailEmployeeFragment empFragment = new DetailEmployeeFragment();
                            empFragment.setArguments(empBundle);
                            FragmentTransaction transaction = getSupportFragmentManager()
                                    .beginTransaction();
                            transaction.replace(R.id.fragment_layout_container, empFragment);
                            transaction.addToBackStack(null);*//*

                        }else UIHelper.showToastLong(getBaseContext(),responseMsg);

                    }else {
                        // UIHelper.showToastLong(getBaseContext(),
                        //        getResources().getString(R.string.failed_get_response_msg));

                        UIHelper.showToastLong(getBaseContext(),responseMsg);
                        prepareBPRDataNotFound();
                    }

                }

                @Override
                public void onFailure(Call<BranchPerformanceModelResponse> call, Throwable t) {
                    //progress.dismiss();
                    UIHelper.showToastLong(DetailQuestionaireActivity.this,
                            getResources().getString(R.string.msg_onfailure_callback));
                    prepareBPRDataNotFound();
                    //Debug error message
                    Log.d("ERROR :" ,t.getLocalizedMessage());

                }
            });
        }finally {

        }

    }

    private void prepareBPRDataNotFound() {
        *//*textBranch.setText(getString(R.string.msg_data_notfound));
        textPersonalNumber.setText(R.string.msg_data_notfound);
        textEmpName.setText(R.string.msg_data_notfound);
        textEmpJob.setText(R.string.msg_data_notfound);*//*
    }

    */
}
