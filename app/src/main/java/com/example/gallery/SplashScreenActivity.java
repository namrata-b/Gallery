package com.example.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = SplashScreenActivity.class.getName();

    public static final int PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Call<Gallery> call = RestClient.getService().getPhotos(Constants.FEATURE, Constants.IMAGE_SIZE, PAGE, Constants.CONSUMER_KEY);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                Log.d(TAG, "success");
                Gallery gallery = response.body();
                ArrayList<Photo> photos = gallery.photos;

                Intent intent = new Intent(SplashScreenActivity.this, GalleryActivity.class);
                intent.putParcelableArrayListExtra(Constants.EXTRA_PHOTOS, photos);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.d(TAG, "failed");
            }
        });
    }
}
