package pl.edu.agh.debtexecutor.api;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class RetrofitClient {
    private static final String API_URL = "http://localhost:8080/api/v1/";
    private final Retrofit client;

    private RetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient();
        client = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public Retrofit getClient() {
        return client;
    }
}
