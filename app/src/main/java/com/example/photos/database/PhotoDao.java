package com.example.photos.database;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(PhotoEntity photoEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PhotoEntity> photos);

    @Query("SELECT * FROM photos WHERE id = :id")
    PhotoEntity getPhotoById(int id);

    @Query("SELECT * FROM photos")
    LiveData<List<PhotoEntity>> getAll();

    @Query("SELECT * FROM photos WHERE favourite = 1")
    LiveData<List<PhotoEntity>> getFavouritePhotos();

    @Query("DELETE FROM photos")
    int deleteAll();

    @Delete
    void deletePhoto(PhotoEntity photoEntity);

    @Query("SELECT COUNT(*) FROM photos")
    int getPhotosCount();

    @Query("SELECT COUNT(*) FROM photos where favourite = 1")
    int getFavouritePhotosCount();
}
