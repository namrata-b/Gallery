package com.example.gallery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namratabandekar on 2016-02-29.
 */
public class Gallery {
    public final String feature;
    public final ArrayList<Photo> photos;

    public Gallery(String feature, ArrayList<Photo> photos) {
        this.feature = feature;
        this.photos = photos;
    }
}
