package comp.rbzeta.branchperformancereport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robyn on 16/09/2016.
 */
public class ResponseMessage {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

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
}
