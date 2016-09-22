package comp.rbzeta.branchperformancereport.contract;

import android.provider.BaseColumns;

import comp.rbzeta.branchperformancereport.R;

/**
 * Created by Robyn on 18/09/2016.
 */
public final class BPRContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "bpr.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";
    private BPRContract(){

    }

    public static class BPR implements BaseColumns{
        private BPR(){}

        public static final String TABLE_NAME = "BPR";
        public static final String COLUMN_BRANCH_CODE = "branch_code";
        public static final String COLUMN_BRANCH_NAME = "branch_name";
        public static final String COLUMN_PERSONAL_NUMBER = "personal_number";
        public static final String COLUMN_EMP_NAME = "emp_name";
        public static final String COLUMN_EMP_JOB = "emp_job";
        public static final String COLUMN_BRINET_TIME = "brinet_time";
        public static final String COLUMN_BRINET_MENU = "brinet_menu";
        public static final String COLUMN_LAS_TIME = "las_time";
        public static final String COLUMN_LAS_MENU = "las_menu";
        public static final String COLUMN_SSO_TIME = "sso_time";
        public static final String COLUMN_SSO_MENU = "sso_menu";
        public static final String COLUMN_NET_TIMEOUT = "network_timeout";
        public static final String COLUMN_NET_OFFLINE = "network_offline";
        public static final String COLUMN_NET_DEVICE = "network_device";
        public static final String COLUMN_OTHER_TIME = "other_time";
        public static final String COLUMN_OTHER_MENU = "other_menu";
        public static final String COLUMN_CREATE_DT = "create_dt";
        public static final String COLUMN_UPDATE_DT = "update_dt";
        public static final String COLUMN_CREATE_USR = "create_user";
        public static final String COLUMN_UPDATE_USER = "update_user";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_BRANCH_CODE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_BRANCH_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PERSONAL_NUMBER + TEXT_TYPE + COMMA_SEP +
                        COLUMN_EMP_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_EMP_JOB + TEXT_TYPE + COMMA_SEP +
                        COLUMN_BRINET_TIME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_BRINET_MENU + TEXT_TYPE + COMMA_SEP +
                        COLUMN_LAS_TIME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_LAS_MENU + TEXT_TYPE + COMMA_SEP +
                        COLUMN_SSO_TIME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_SSO_MENU + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NET_TIMEOUT + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NET_OFFLINE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NET_DEVICE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_OTHER_TIME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_OTHER_MENU + TEXT_TYPE + " )";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
