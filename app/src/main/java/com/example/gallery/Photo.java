package com.example.gallery;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public class Photo implements Parcelable {
    public final String name;
    public final String image_url;
    public final int width;
    public final int height;
    public final String description;

    public Photo(String name, String image_url, int width, int height, String description) {
        this.name = name;
        this.image_url = image_url;
        this.width = width;
        this.height = height;
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // create a bundle for the key value pairs
        Bundle bundle = new Bundle();

        // insert the key value pairs to the bundle
        bundle.putString("name", name);
        bundle.putString("image_url", image_url);
        bundle.putInt("width", width);
        bundle.putInt("height", height);
        bundle.putString("description", description);

        // write the key value pairs to the parcel
        dest.writeBundle(bundle);
    }

    /**
     * Creator required for class implementing the parcelable interface.
     */
    public static final Parcelable.Creator<Photo> CREATOR = new Creator<Photo>() {

        @Override
        public Photo createFromParcel(Parcel source) {
            // read the bundle containing key value pairs from the parcel
            Bundle bundle = source.readBundle();

            // instantiate a person using values from the bundle
            return new Photo(
                    bundle.getString("name"),
                    bundle.getString("image_url"),
                    bundle.getInt("width"),
                    bundle.getInt("height"),
                    bundle.getString("description"));
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
