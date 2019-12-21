package com.example.photos.utilities;

import com.example.photos.model.PhotoEntity;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_PHOTO_TITLE_0 = "Belfry of Bruges";
    private static final String SAMPLE_PHOTO_TITLE_1 = "Walking across the city park";
    private static final String SAMPLE_PHOTO_TITLE_2 = "Chocolate Baked Donuts";

    private static final String SAMPLE_PHOTO_FILENAME_0 = "photo_1575410568144_6a77c281eba5";
    private static final String SAMPLE_PHOTO_FILENAME_1 = "photo_1574296000154_b3a070ef5bda";
    private static final String SAMPLE_PHOTO_FILENAME_2 = "photo_1576021220401_9f1453159c62";

    public static List<PhotoEntity> getPhotos() {
        List<PhotoEntity> photos = new ArrayList<>();
        photos.add(new PhotoEntity(0, SAMPLE_PHOTO_TITLE_0, SAMPLE_PHOTO_FILENAME_0));
        photos.add(new PhotoEntity(1, SAMPLE_PHOTO_TITLE_1, SAMPLE_PHOTO_FILENAME_1));
        photos.add(new PhotoEntity(2, SAMPLE_PHOTO_TITLE_2, SAMPLE_PHOTO_FILENAME_2));
        return photos;
    }
}
