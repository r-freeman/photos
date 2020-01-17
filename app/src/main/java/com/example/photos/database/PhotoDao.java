package com.example.photos.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * The PhotoDao (Data Access Object) class defines all of the operations and SQL queries
 * for our interactions with the database. Room annotations like @Insert, @Delete are used to define
 * database operations.
 */
@Dao
public interface PhotoDao {
    // If we tried to insert a photo with the same primary key we might cause an exception
    // However, with OnConflictStrategy.REPLACE we are "upserting" the existing photo.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(PhotoEntity photoEntity);

    // Same as above only with a list of photos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PhotoEntity> photos);

    // Returns an instance of PhotoEntity for a given id
    @Query("SELECT * FROM photos WHERE id = :id")
    PhotoEntity getPhotoById(int id);

    // Returns a LiveData List of PhotoEntities
    @Query("SELECT * FROM photos")
    LiveData<List<PhotoEntity>> getAll();

    // Returns a LiveData List of favourite photos
    @Query("SELECT * FROM photos WHERE favourite = 1")
    LiveData<List<PhotoEntity>> getFavouritePhotos();

    // Deletes all photos
    @Query("DELETE FROM photos")
    int deleteAll();

    // Deletes a specific PhotoEntity
    @Delete
    void deletePhoto(PhotoEntity photoEntity);

    // Returns photos count, see unit test
    @Query("SELECT COUNT(*) FROM photos")
    int getPhotosCount();

    // Returns favourite photo count
    @Query("SELECT COUNT(*) FROM photos where favourite = 1")
    int getFavouritePhotosCount();
}
