package comp.rbzeta.branchperformancereport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robyn on 16/09/2016.
 */
public class ResponseMessageResponse {

    @SerializedName("response_message")
    private ResponseMessage responseMessage;

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }
}
