package pl.age.edu.api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String API_URL = "http://localhost:8080/api/v1/";

    public static Retrofit getRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
