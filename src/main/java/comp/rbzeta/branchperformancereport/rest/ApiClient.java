package comp.rbzeta.branchperformancereport.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Robyn on 14/09/2016.
 */
public class ApiClient {
    //public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BASE_URL = "http://202.169.39.114/ws/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
