package com.example.gallery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator.SizeCalculatorDelegate;
import com.squareup.picasso.Picasso;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> implements SizeCalculatorDelegate {
    public static final @DrawableRes int[] IMAGES = new int[] {
            R.drawable.photo_1,
            R.drawable.photo_2,
            R.drawable.photo_3,
            R.drawable.photo_4,
            R.drawable.photo_5,
            R.drawable.photo_6,
            R.drawable.photo_7,
            R.drawable.photo_8,
            R.drawable.photo_9,
            R.drawable.photo_10,
            R.drawable.photo_11,
            R.drawable.photo_12,
            R.drawable.photo_13,
            R.drawable.photo_14,
            R.drawable.photo_15,
            R.drawable.photo_16,
            R.drawable.photo_17
    };

    public static final int IMAGE_COUNT = 100;

    private Context mContext;

    @Override
    public double aspectRatioForIndex(int index) {
        // Precaution, have better handling for this in greedo-layout
        if (index >= IMAGES.length) return 1.0;

        Drawable drawable = mContext.getResources().getDrawable(IMAGES[index]);
        if (drawable != null) {
            return (double) drawable.getIntrinsicWidth() / (double) drawable.getIntrinsicHeight();
        } else {
            return 1.0;
        }
    }

    public GalleryAdapter(Context context) {
        mContext = context;
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
                .load(IMAGES[position])
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return IMAGES.length;
    }

}
