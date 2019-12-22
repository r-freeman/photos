package com.example.photos.utilities;

import com.example.photos.model.PhotoEntity;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_PHOTO_TITLE_0 = "Belfry of Bruges";
    private static final String SAMPLE_PHOTO_TITLE_1 = "Walking across the city park";
    private static final String SAMPLE_PHOTO_TITLE_2 = "Chocolate Baked Donuts";
    private static final String SAMPLE_PHOTO_TITLE_3 = "Skate life";
    private static final String SAMPLE_PHOTO_TITLE_4 = "Orcas gliding through the fjord";
    private static final String SAMPLE_PHOTO_TITLE_5 = "Children learning to sew";
    private static final String SAMPLE_PHOTO_TITLE_6 = "The road ahead";
    private static final String SAMPLE_PHOTO_TITLE_7 = "Wall brick";
    private static final String SAMPLE_PHOTO_TITLE_8 = "A sea turtle";

    private static final String SAMPLE_PHOTO_FILENAME_0 = "photo_1575410568144_6a77c281eba5";
    private static final String SAMPLE_PHOTO_FILENAME_1 = "photo_1574296000154_b3a070ef5bda";
    private static final String SAMPLE_PHOTO_FILENAME_2 = "photo_1576021220401_9f1453159c62";
    private static final String SAMPLE_PHOTO_FILENAME_3 = "photo_1574347693846_502bd1c08561";
    private static final String SAMPLE_PHOTO_FILENAME_4 = "photo_1574969970937_a90cdcbeea2e";
    private static final String SAMPLE_PHOTO_FILENAME_5 = "photo_1576830886922_02b61aee42ad";
    private static final String SAMPLE_PHOTO_FILENAME_6 = "photo_1575188404825_da8543438c37";
    private static final String SAMPLE_PHOTO_FILENAME_7 = "photo_1575389168138_39bb16c6d809";
    private static final String SAMPLE_PHOTO_FILENAME_8 = "photo_1575488465950_72feb253e889";

    public static List<PhotoEntity> getPhotos() {
        List<PhotoEntity> photos = new ArrayList<>();
        photos.add(new PhotoEntity(0, SAMPLE_PHOTO_TITLE_0, SAMPLE_PHOTO_FILENAME_0));
        photos.add(new PhotoEntity(1, SAMPLE_PHOTO_TITLE_1, SAMPLE_PHOTO_FILENAME_1));
        photos.add(new PhotoEntity(2, SAMPLE_PHOTO_TITLE_2, SAMPLE_PHOTO_FILENAME_2));
        photos.add(new PhotoEntity(3, SAMPLE_PHOTO_TITLE_3, SAMPLE_PHOTO_FILENAME_3));
        photos.add(new PhotoEntity(4, SAMPLE_PHOTO_TITLE_4, SAMPLE_PHOTO_FILENAME_4));
        photos.add(new PhotoEntity(5, SAMPLE_PHOTO_TITLE_5, SAMPLE_PHOTO_FILENAME_5));
        photos.add(new PhotoEntity(6, SAMPLE_PHOTO_TITLE_6, SAMPLE_PHOTO_FILENAME_6));
        photos.add(new PhotoEntity(7, SAMPLE_PHOTO_TITLE_7, SAMPLE_PHOTO_FILENAME_7));
        photos.add(new PhotoEntity(8, SAMPLE_PHOTO_TITLE_8, SAMPLE_PHOTO_FILENAME_8));
        return photos;
    }
}
