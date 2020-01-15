package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.photos.database.AppRepository;
import com.example.photos.database.PhotoEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PhotoViewModel extends AndroidViewModel {
    public MutableLiveData<PhotoEntity> mLivePhoto =
            new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(final int photoId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PhotoEntity photo = mRepository.getPhotoById(photoId);
                mLivePhoto.postValue(photo);
            }
        });
    }

    public void toggleFavouritePhoto() {
        PhotoEntity photo = mLivePhoto.getValue();
        if (photo != null) {
            if (photo.getFavourite() == 1) {
                photo.setFavourite(0);
            } else {
                photo.setFavourite(1);
            }
            mRepository.insertPhoto(photo);
        }
    }
}
