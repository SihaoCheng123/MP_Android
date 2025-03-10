package com.example.mealplanner.io.api;

import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String URL_HOME_PHONE = "http://192.168.1.154:8080/api/";
    private static final String URL_SCHOOL = "http://192.168.73.209:8080/api/";

    private static final String ANDROID_URL = "http://10.0.2.2:8080/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ANDROID_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
