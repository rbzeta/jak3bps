package comp.rbzeta.branchperformancereport.aplication;

import android.app.Application;

import comp.rbzeta.branchperformancereport.receiver.ConnectivityReceiver;

/**
 * Created by Robyn on 18/09/2016.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
