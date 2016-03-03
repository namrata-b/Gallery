package com.example.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.fivehundredpx.greedolayout.GreedoLayoutManager;
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {
    private int mCurrentPage = 1;

    private boolean loading = false;
    int pastVisibleItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Photo> photos = bundle.getParcelableArrayList(Constants.EXTRA_PHOTOS);
        // Create an instance of the GreedoLayoutManager and pass it to the RecyclerView
        final GalleryAdapter recyclerAdapter = new GalleryAdapter(this, photos);
        final GreedoLayoutManager layoutManager = new GreedoLayoutManager(recyclerAdapter);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisibleItems)*1.1 >= totalItemCount) {
                            loading = true;
                            mCurrentPage++;
                            //fetch new data
                            Call<Gallery> call = RestClient.getService().getPhotos(Constants.FEATURE, Constants.IMAGE_SIZE, mCurrentPage, Constants.CONSUMER_KEY);
                            call.enqueue(new Callback<Gallery>() {
                                @Override
                                public void onResponse(Call<Gallery> call, Response<Gallery> response) {
//                                    Log.d(TAG, "success");
                                    Gallery gallery = response.body();
                                    ArrayList<Photo> photos = gallery.photos;
                                    recyclerAdapter.updatePhotos(photos);
                                    loading = false;
                                }

                                @Override
                                public void onFailure(Call<Gallery> call, Throwable t) {
//                                    Log.d(TAG, "failed");
                                }
                            });
                        }
                    }
                }
            }
        });
        // Set the max row height in pixels
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int maxRowHeight = metrics.heightPixels / 3;
        layoutManager.setMaxRowHeight(maxRowHeight);

        // If you would like to add spacing between items (Note, MeasUtils is in the sample project)
        int spacing = MeasUtils.dpToPx(4, this);
        recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
    }
}
