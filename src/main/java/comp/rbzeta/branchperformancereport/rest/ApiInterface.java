package comp.rbzeta.branchperformancereport.rest;

import comp.rbzeta.branchperformancereport.contract.BPRContract;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModelResponse;
import comp.rbzeta.branchperformancereport.model.ResponseMessageResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Robyn on 14/09/2016.
 */
public interface ApiInterface {

    @POST("brp/ws/insert_bpr.php")
    Call<ResponseMessageResponse> saveBranchPerformanceReport(@Body BranchPerformanceModel bpr);

    @GET("brp/ws/read_latest_bpr.php")
    Call<BranchPerformanceModelResponse> getLatestBranchPerformanchReport();

    @GET("brp/ws/read_row_by_id.php")
    Call<BranchPerformanceModelResponse> getBPRByID(@Query("bpr_id") int bprId);
}
