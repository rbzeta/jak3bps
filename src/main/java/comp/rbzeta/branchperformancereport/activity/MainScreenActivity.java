package comp.rbzeta.branchperformancereport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.adapter.BPRAdapter;
import comp.rbzeta.branchperformancereport.aplication.MyApplication;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.handler.BprDBHandler;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModelResponse;
import comp.rbzeta.branchperformancereport.receiver.ConnectivityReceiver;
import comp.rbzeta.branchperformancereport.recyclerview.ClickListener;
import comp.rbzeta.branchperformancereport.recyclerview.DividerItemDecoration;
import comp.rbzeta.branchperformancereport.recyclerview.RecyclerTouchListener;
import comp.rbzeta.branchperformancereport.rest.ApiClient;
import comp.rbzeta.branchperformancereport.rest.ApiInterface;
import comp.rbzeta.branchperformancereport.util.UIHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenActivity extends AppCompatActivity
implements ConnectivityReceiver.ConnectivityReceiverListener{

    private static final int TIME_INTERVAL = 2000;
    public static final String MSG_ID_BPR =
            "comp.rbzeta.branchperformancereport.activity.MainScreenActivity.MSG_ID_BPR";
    public static final String MSG_SAVED_USER =
            "comp.rbzeta.branchperformancereport.activity.MainScreenActivity.MSG_SAVED_USER";
    private long mBackPressed;
    private List<BranchPerformanceModel> bprList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BPRAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.swipe_to_refresh);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.orange));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new BPRAdapter(bprList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            public void onClick(View view, int position) {

                BranchPerformanceModel bpr = bprList.get(position);

                if (bpr != null) {
                    Intent intent = new Intent(MainScreenActivity.this, DetailQuestionaireActivity.class);
                    //Intent intent = new Intent(MainScreenActivity.this, HideShowAppBarLayout.class);
                    intent.putExtra(BPRContract.BPR._ID,bpr.getBprID());
                    intent.putExtra(BPRContract.BPR.COLUMN_BRANCH_CODE,bpr.getBranchCode());
                    intent.putExtra(BPRContract.BPR.COLUMN_BRANCH_NAME,bpr.getBranchName());
                    intent.putExtra(BPRContract.BPR.COLUMN_PERSONAL_NUMBER,bpr.getPersonalNumber());
                    intent.putExtra(BPRContract.BPR.COLUMN_EMP_NAME,bpr.getEmpName());
                    intent.putExtra(BPRContract.BPR.COLUMN_EMP_JOB,bpr.getEmpJob());
                    intent.putExtra(BPRContract.BPR.COLUMN_BRINET_TIME,bpr.getBrinetTime());
                    intent.putExtra(BPRContract.BPR.COLUMN_BRINET_MENU,bpr.getBrinetMenu());
                    intent.putExtra(BPRContract.BPR.COLUMN_LAS_TIME,bpr.getLasTime());
                    intent.putExtra(BPRContract.BPR.COLUMN_LAS_MENU,bpr.getLasMenu());
                    intent.putExtra(BPRContract.BPR.COLUMN_SSO_TIME,bpr.getSsoTime());
                    intent.putExtra(BPRContract.BPR.COLUMN_SSO_MENU,bpr.getSsoMenu());
                    intent.putExtra(BPRContract.BPR.COLUMN_NET_TIMEOUT,bpr.getNetworkTimeout());
                    intent.putExtra(BPRContract.BPR.COLUMN_NET_OFFLINE,bpr.getNetworkOffline());
                    intent.putExtra(BPRContract.BPR.COLUMN_NET_DEVICE,bpr.getNetworkDevice());
                    intent.putExtra(BPRContract.BPR.COLUMN_OTHER_TIME,bpr.getOtherTime());
                    intent.putExtra(BPRContract.BPR.COLUMN_OTHER_MENU,bpr.getOtherMenu());
                    startActivity(intent);
                }


                //Toast.makeText(getApplicationContext(), bpr.getEmpName() + " is selected!", Toast.LENGTH_SHORT).show();
            }


            public void onLongClick(View view, int position) {

            }
        }));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isConnected()){
                    bprList.clear();
                    prepareBPRDataOnline();
                }else{
                    UIHelper.showCustomSnackBar(findViewById(R.id.coordinatorLayout),
                            getResources().getString(R.string.msg_no_internet),Color.RED);
                    mSwipeRefreshLayout.setRefreshing(false);
                }


            }
        });

        //testing with sample data
        //prepareBPRDataTest();

        //check connection and prepare the data online
        if (isConnected()) {

            prepareBPRDataOnline();

        } else {
            UIHelper.showCustomSnackBar(findViewById(R.id.coordinatorLayout),
                    getResources().getString(R.string.msg_no_internet),Color.RED);
            prepareBPRDataNotFound();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BprDBHandler myHandler = new BprDBHandler(getBaseContext());
                BranchPerformanceModel bpr = myHandler.getSavedEmployeeData();

                if (bpr.getBranchCode() == null) {
                    Intent intent = new Intent(MainScreenActivity.this,DataEmployeeActivity.class);
                    startActivity(intent);
                }else {
                    HashMap<String,String> empMap = new HashMap<>();
                    empMap.put(BPRContract.BPR.COLUMN_BRANCH_CODE,bpr.getBranchCode());
                    empMap.put(BPRContract.BPR.COLUMN_BRANCH_NAME,bpr.getBranchName());
                    empMap.put(BPRContract.BPR.COLUMN_PERSONAL_NUMBER,bpr.getPersonalNumber());
                    empMap.put(BPRContract.BPR.COLUMN_EMP_NAME,bpr.getEmpName());
                    empMap.put(BPRContract.BPR.COLUMN_EMP_JOB,bpr.getEmpJob());
                    Intent intent = new Intent(MainScreenActivity.this,ViewEmployeeData.class);
                    intent.putExtra(MSG_SAVED_USER,empMap);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean isConnected() {
        boolean isConnected = ConnectivityReceiver.isConnected();

        return isConnected;

    }


    private void prepareBPRDataOnline() {
        //final ProgressDialog progress= ProgressDialog.show(this,"Loading..","Please wait",false,false);
        try {
            mSwipeRefreshLayout.setRefreshing(true);

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<BranchPerformanceModelResponse> call = apiService.getLatestBranchPerformanchReport();
            call.enqueue(new Callback<BranchPerformanceModelResponse>() {
                @Override
                public void onResponse(Call<BranchPerformanceModelResponse> call,
                                       Response<BranchPerformanceModelResponse> response) {
                    //progress.dismiss();

                    List<BranchPerformanceModel> resultBPRList = response.body().getResults();

                    int responseCode = response.body().getCode();
                    String responseMsg = response.body().getMessage();

                    if (resultBPRList != null) {

                        if (responseCode == 1) {

                            for (BranchPerformanceModel bpr : resultBPRList) {
                                bprList.add(bpr);
                            }

                            mAdapter.notifyDataSetChanged();

                        }else UIHelper.showToastLong(getBaseContext(),responseMsg);

                    }else {
                       // UIHelper.showToastLong(getBaseContext(),
                        //        getResources().getString(R.string.failed_get_response_msg));

                        UIHelper.showToastLong(getBaseContext(),responseMsg);
                        prepareBPRDataNotFound();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);

                }

                @Override
                public void onFailure(Call<BranchPerformanceModelResponse> call, Throwable t) {
                    //progress.dismiss();
                    mSwipeRefreshLayout.setRefreshing(false);
                    UIHelper.showToastLong(MainScreenActivity.this,
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
        BranchPerformanceModel bpr = new
                BranchPerformanceModel(getResources().getString(R.string.msg_data_notfound),"-","-");
        bprList.add(bpr);
        mAdapter.notifyDataSetChanged();
    }

    //method to provide dummy data
    private void prepareBPRDataTest() {
        BranchPerformanceModel bpr = new BranchPerformanceModel("Robyn Bagus Seta","Kanwil Jakarta 3","Staff");
        bprList.add(bpr);

        bpr = new BranchPerformanceModel("Ezio Muazzam Altair","Kanca Mangga Dua","Pinca");
        bprList.add(bpr);

        bpr = new BranchPerformanceModel("Eiji Muafkar Alshain","Kanca Pangkal Pinang","Manajer Operasional");
        bprList.add(bpr);

        bpr = new BranchPerformanceModel("Nurpani","Bukopin Gunung Sahari","Back Office");
        bprList.add(bpr);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else UIHelper.showToastLong(getBaseContext(),getResources().getString(R.string.msg_back_btn_exit));


        mBackPressed = System.currentTimeMillis();
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        if(!isConnected) {
            UIHelper.showCustomSnackBar(findViewById(R.id.coordinatorLayout),
                    getResources().getString(R.string.msg_no_internet), Color.RED);
        }else {
            prepareBPRDataOnlineAfterLostConnection();
        }
    }


    private void prepareBPRDataOnlineAfterLostConnection() {
        bprList.clear();
        prepareBPRDataOnline();
    }

    @Override
    protected void onResume(){
        super.onResume();

        MyApplication.getInstance().setConnectivityListener(this);

    }
}
