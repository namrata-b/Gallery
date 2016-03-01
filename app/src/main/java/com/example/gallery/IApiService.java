package com.example.gallery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public interface IApiService {

    @GET("/v1/photos")
    public Call<Gallery> getPhotos(@Query("feature") String feature, @Query("rpp") int rpp, @Query("consumer_key") String consumerKey);

}
