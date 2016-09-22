package comp.rbzeta.branchperformancereport.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;

/**
 * Created by Robyn on 21/09/2016.
 */

public class BprDBHandler extends SQLiteOpenHelper {

    public BprDBHandler(Context context) {
        super(context, BPRContract.DATABASE_NAME, null, BPRContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BPRContract.BPR.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BPRContract.BPR.DELETE_TABLE);
        onCreate(db);

    }
    
    public boolean saveEmployeeData(BranchPerformanceModel bpr){

        ContentValues values = new ContentValues();
        values.put(BPRContract.BPR.COLUMN_BRANCH_CODE,bpr.getBranchCode());
        values.put(BPRContract.BPR.COLUMN_BRANCH_NAME,bpr.getBranchName());
        values.put(BPRContract.BPR.COLUMN_PERSONAL_NUMBER,bpr.getPersonalNumber());
        values.put(BPRContract.BPR.COLUMN_EMP_NAME,bpr.getEmpName());
        values.put(BPRContract.BPR.COLUMN_EMP_JOB,bpr.getEmpJob());

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.insert(BPRContract.BPR.TABLE_NAME,null,values);
            return true;
        }catch (SQLiteException e){
            return false;
        }finally {
            db.close();
        }

    }

    public BranchPerformanceModel getSavedEmployeeData(){
        BranchPerformanceModel bpr = new BranchPerformanceModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BPRContract.BPR.COLUMN_BRANCH_CODE,
                BPRContract.BPR.COLUMN_BRANCH_NAME,
                BPRContract.BPR.COLUMN_PERSONAL_NUMBER,
                BPRContract.BPR.COLUMN_EMP_NAME,
                BPRContract.BPR.COLUMN_EMP_JOB

        };

        String selection = BPRContract.BPR.COLUMN_PERSONAL_NUMBER + " LIKE ?";
        String[] selectionArgs = { "" };

        String sortOrder = BPRContract.BPR.COLUMN_PERSONAL_NUMBER + " DESC";

        Cursor c = db.query(
                BPRContract.BPR.TABLE_NAME,                           // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        try{
            while (c.moveToNext()){
                c.moveToFirst();

                bpr.setBranchCode(c.getString(0));
                bpr.setBranchName(c.getString(1));
                bpr.setPersonalNumber(c.getString(2));
                bpr.setEmpName(c.getString(3));
                bpr.setEmpJob(c.getString(4));
            }


        }catch (SQLiteException e){

        }
        finally {
            c.close();
            db.close();
        }
        return bpr;
    }

    public void deleteSavedEmployeeData(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(BPRContract.BPR.TABLE_NAME, null, null);
    }
}
