package com.example.gallery;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by namratabandekar on 2016-03-01.
 */
public class App extends Application {
    private static final int PICASSO_DISK_CACHE_SIZE = 1024 * 1024 * 100; // 100MB

    @Override
    public void onCreate() {
        super.onCreate();

        // Increase picasso cache size
        OkHttpDownloader okHttpDownloader = new OkHttpDownloader(this, PICASSO_DISK_CACHE_SIZE);
        Picasso picasso = new Picasso.Builder(this)
                .downloader(okHttpDownloader)
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}