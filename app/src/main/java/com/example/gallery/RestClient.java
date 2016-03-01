package com.example.gallery;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public class RestClient {
    private static final String BASE_URL = "https://api.500px.com/";
    private static final Retrofit REST_ADAPTER = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final IApiService API_SERVICE = REST_ADAPTER.create(IApiService.class);

    public static IApiService getService() {
        return API_SERVICE;
    }
}
