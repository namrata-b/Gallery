package com.example.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator.SizeCalculatorDelegate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> implements SizeCalculatorDelegate {
    public static ArrayList<Photo> mImages;

    private Context mContext;

    @Override
    public double aspectRatioForIndex(int index) {
        // Precaution, have better handling for this in greedo-layout
        if (index >= mImages.size()) return 1.0;

        Photo photo = mImages.get(index);
        if (photo != null) {
            return (double) photo.width / (double) photo.height;
        } else {
            return 1.0;
        }
    }

    public GalleryAdapter(Context context, ArrayList<Photo> photos) {
        mContext = context;
        mImages = photos;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public PhotoViewHolder(ImageView imageView) {
            super(imageView);
            mImageView = imageView;
        }
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        return new PhotoViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        Context context = holder.mImageView.getContext();

        Picasso.with(context)
                .load(mImages.get(position).image_url)
                .into(holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailsActivity.class);

                // Interesting data to pass across are the thumbnail size/location, the
                // resourceId of the source bitmap, the picture description, and the
                // orientation (to avoid returning back to an obsolete configuration if
                // the device rotates again in the meantime)

                int[] screenLocation = new int[2];
                view.getLocationOnScreen(screenLocation);

                //Pass the image title and url to DetailsActivity
                i.putExtra(DetailsActivity.EXTRA_LEFT, screenLocation[0]).
                        putExtra(DetailsActivity.EXTRA_TOP, screenLocation[1]).
                        putExtra(DetailsActivity.EXTRA_WIDTH, view.getWidth()).
                        putExtra(DetailsActivity.EXTRA_HEIGHT, view.getHeight()).
                        putExtra(DetailsActivity.EXTRA_IMAGE, mImages.get(position));

                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

}
