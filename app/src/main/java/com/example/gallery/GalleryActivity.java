package com.example.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.fivehundredpx.greedolayout.GreedoLayoutManager;
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTOS = "photos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Photo> photos = bundle.getParcelableArrayList(EXTRA_PHOTOS);
        // Create an instance of the GreedoLayoutManager and pass it to the RecyclerView
        GalleryAdapter recyclerAdapter = new GalleryAdapter(this, photos);
        GreedoLayoutManager layoutManager = new GreedoLayoutManager(recyclerAdapter);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        // Set the max row height in pixels
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int maxRowHeight = metrics.heightPixels / 3;
        layoutManager.setMaxRowHeight(maxRowHeight);

        // If you would like to add spacing between items (Note, MeasUtils is in the sample project)
        int spacing = MeasUtils.dpToPx(4, this);
        recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
    }
}
