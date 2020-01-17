package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.photos.database.AppRepository;
import com.example.photos.database.PhotoEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The PhotoViewModel corresponds to PhotoActivity, each activity should have its own ViewModel.
 * The ViewModel allows us to observe changes in the database and update view as necessary.
 */
public class PhotoViewModel extends AndroidViewModel {
    // MutableLiveData is a sub class of LiveData again we want to observe changes in the data
    // and update view as necessary. However, MutableLiveData allows us access to setValue and postValue to notify
    // the UI of changes.
    public MutableLiveData<PhotoEntity> mLivePhoto =
            new MutableLiveData<>();
    private AppRepository mRepository;
    // set up executor for database operations
    private Executor executor = Executors.newSingleThreadExecutor();

    // constructor
    public PhotoViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    /**
     * Load photo from database into the PhotoViewModel
     * @param photoId
     */
    public void loadData(final int photoId) {
        // executing in a background thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PhotoEntity photo = mRepository.getPhotoById(photoId);
                mLivePhoto.postValue(photo);
            }
        });
    }

    // when the favourite fab button is clicked
    public void toggleFavouritePhoto() {
        // retrieve the photos
        PhotoEntity photo = mLivePhoto.getValue();
        if (photo != null) {
            // toggle the favourite attribute
            if (photo.getFavourite() == 1) {
                photo.setFavourite(0);
            } else {
                photo.setFavourite(1);
            }
            // insert the updated photo
            mRepository.insertPhoto(photo);
        }
    }

    // delete photo from the database
    public void deletePhoto() {
        mRepository.deletePhoto(mLivePhoto.getValue());
    }
}
