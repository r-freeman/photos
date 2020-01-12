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

    @Query("SELECT * FROM liked_photos WHERE id = :id")
    PhotoEntity getPhotoById(int id);

    @Query("SELECT * FROM liked_photos")
    LiveData<List<PhotoEntity>> getAll();

    @Query("DELETE FROM liked_photos")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM liked_photos")
    int getCount();
}
