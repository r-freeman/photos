package com.example.photos.database;

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

    @Delete
    void deleteNote(PhotoEntity photoEntity);

    @Query("SELECT * FROM photos WHERE id = :id")
    PhotoEntity getPhotoById(int id);

    @Query("SELECT * FROM photos")
    LiveData<List<PhotoEntity>> getAll();

    @Query("SELECT * FROM photos WHERE liked = 1")
    LiveData<List<PhotoEntity>> getAllLikedPhotos();

    @Query("DELETE FROM photos")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM photos")
    int getCount();

    @Query("SELECT COUNT(*) FROM photos where liked = 1")
    int getLikedCount();
}
