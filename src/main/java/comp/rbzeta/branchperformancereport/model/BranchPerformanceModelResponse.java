package comp.rbzeta.branchperformancereport.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Robyn on 14/09/2016.
 */
public class BranchPerformanceModelResponse {

    @SerializedName("response_bpr")
    private List<BranchPerformanceModel> results;

    @SerializedName("bpr")
    private BranchPerformanceModel bpr;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public List<BranchPerformanceModel> getResults() {
        return results;
    }

    public void setResults(List<BranchPerformanceModel> results) {
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BranchPerformanceModel getBpr() {
        return bpr;
    }

    public void setBpr(BranchPerformanceModel bpr) {
        this.bpr = bpr;
    }
}
