package comp.rbzeta.branchperformancereport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.HashMap;
import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.adapter.ViewPagerAdapter;
import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.fragment.HomeHistoryFragment;
import comp.rbzeta.branchperformancereport.fragment.HomeSummaryFragment;
import comp.rbzeta.branchperformancereport.handler.BprDBHandler;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;
import comp.rbzeta.branchperformancereport.util.UIHelper;

public class MainScreenActivity extends AppCompatActivity
{

    private static final int TIME_INTERVAL = 2000;
    public static final String MSG_ID_BPR =
            "comp.rbzeta.branchperformancereport.activity.MainScreenActivity.MSG_ID_BPR";
    public static final String MSG_SAVED_USER =
            "comp.rbzeta.branchperformancereport.activity.MainScreenActivity.MSG_SAVED_USER";
    private long mBackPressed;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.swipe_to_refresh);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        viewPager = (ViewPager) findViewById(R.id.viewpagerHome);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabHome);
        tabLayout.setupWithViewPager(viewPager);

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


    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
        }
        else UIHelper.showToastLong(getBaseContext(),getResources().getString(R.string.msg_back_btn_exit));


        mBackPressed = System.currentTimeMillis();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeHistoryFragment(),
                getString(R.string.title_fragment_home_hist));
        adapter.addFragment(new HomeSummaryFragment(),
                getString(R.string.title_fragment_home_summary));
        viewPager.setAdapter(adapter);
    }
}
