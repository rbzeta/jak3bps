package comp.rbzeta.branchperformancereport.model;

import com.google.gson.annotations.SerializedName;

import comp.rbzeta.branchperformancereport.contract.BPRContract;

/**
 * Created by Robyn on 14/09/2016.
 */
public class BranchPerformanceModel {

    @SerializedName("id")
    private int bprID;
    @SerializedName(BPRContract.BPR.COLUMN_BRANCH_CODE)
    private String branchCode;
    @SerializedName(BPRContract.BPR.COLUMN_BRANCH_NAME)
    private String branchName;
    @SerializedName(BPRContract.BPR.COLUMN_PERSONAL_NUMBER)
    private String personalNumber;
    @SerializedName(BPRContract.BPR.COLUMN_EMP_NAME)
    private String empName;
    @SerializedName(BPRContract.BPR.COLUMN_EMP_JOB)
    private String empJob;
    @SerializedName(BPRContract.BPR.COLUMN_BRINET_TIME)
    private String brinetTime;
    @SerializedName(BPRContract.BPR.COLUMN_BRINET_MENU)
    private String brinetMenu;
    @SerializedName(BPRContract.BPR.COLUMN_LAS_TIME)
    private String lasTime;
    @SerializedName(BPRContract.BPR.COLUMN_LAS_MENU)
    private String lasMenu;
    @SerializedName(BPRContract.BPR.COLUMN_SSO_TIME)
    private String ssoTime;
    @SerializedName(BPRContract.BPR.COLUMN_SSO_MENU)
    private String ssoMenu;
    @SerializedName(BPRContract.BPR.COLUMN_OTHER_TIME)
    private String otherTime;
    @SerializedName(BPRContract.BPR.COLUMN_OTHER_MENU)
    private String otherMenu;
    @SerializedName(BPRContract.BPR.COLUMN_NET_TIMEOUT)
    private String networkTimeout;
    @SerializedName(BPRContract.BPR.COLUMN_NET_OFFLINE)
    private String networkOffline;
    @SerializedName(BPRContract.BPR.COLUMN_NET_DEVICE)
    private String networkDevice;

    public BranchPerformanceModel(){

    }

    public BranchPerformanceModel(int bprID, String branchCode, String branchName, String personalNumber,
                                  String empName, String empJob, String brinetTime,
                                  String brinetMenu, String lasTime, String lasMenu,
                                  String ssoTime, String ssoMenu, String otherTime,
                                  String otherMenu, String networkTimeout, String networkOffline,
                                  String networkDevice) {
        this.bprID = bprID;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.personalNumber = personalNumber;
        this.empName = empName;
        this.empJob = empJob;
        this.brinetTime = brinetTime;
        this.brinetMenu = brinetMenu;
        this.lasTime = lasTime;
        this.lasMenu = lasMenu;
        this.ssoTime = ssoTime;
        this.ssoMenu = ssoMenu;
        this.otherTime = otherTime;
        this.otherMenu = otherMenu;
        this.networkTimeout = networkTimeout;
        this.networkOffline = networkOffline;
        this.networkDevice = networkDevice;
    }

    public BranchPerformanceModel(String empName, String branchName, String empJob){
        this.empName = empName;
        this.branchName = branchName;
        this.empJob = empJob;
    }

    public int getBprID() {
        return bprID;
    }

    public void setBprID(int bprID) {
        this.bprID = bprID;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getBrinetTime() {
        return brinetTime;
    }

    public void setBrinetTime(String brinetTime) {
        this.brinetTime = brinetTime;
    }

    public String getBrinetMenu() {
        return brinetMenu;
    }

    public void setBrinetMenu(String brinetMenu) {
        this.brinetMenu = brinetMenu;
    }

    public String getLasTime() {
        return lasTime;
    }

    public void setLasTime(String lasTime) {
        this.lasTime = lasTime;
    }

    public String getLasMenu() {
        return lasMenu;
    }

    public void setLasMenu(String lasMenu) {
        this.lasMenu = lasMenu;
    }

    public String getSsoTime() {
        return ssoTime;
    }

    public void setSsoTime(String ssoTime) {
        this.ssoTime = ssoTime;
    }

    public String getSsoMenu() {
        return ssoMenu;
    }

    public void setSsoMenu(String ssoMenu) {
        this.ssoMenu = ssoMenu;
    }

    public String getOtherTime() {
        return otherTime;
    }

    public void setOtherTime(String otherTime) {
        this.otherTime = otherTime;
    }

    public String getOtherMenu() {
        return otherMenu;
    }

    public void setOtherMenu(String otherMenu) {
        this.otherMenu = otherMenu;
    }

    public String getNetworkTimeout() {
        return networkTimeout;
    }

    public void setNetworkTimeout(String networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public String getNetworkOffline() {
        return networkOffline;
    }

    public void setNetworkOffline(String networkOffline) {
        this.networkOffline = networkOffline;
    }

    public String getNetworkDevice() {
        return networkDevice;
    }

    public void setNetworkDevice(String networkDevice) {
        this.networkDevice = networkDevice;
    }
}
