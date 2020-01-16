package com.example.photos.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photos.PhotoActivity;
import com.example.photos.R;
import com.example.photos.database.PhotoEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.photos.utilities.Constants.DRAWABLE_PATH;
import static com.example.photos.utilities.Constants.PHOTO_ID;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final List<PhotoEntity> mPhotos;
    private final Context mContext;

    public PhotosAdapter(List<PhotoEntity> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    public static int getPhotoId(Context mContext, String photoFileName) {
        return mContext.getResources().getIdentifier(DRAWABLE_PATH + photoFileName, null, mContext.getPackageName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PhotoEntity photo = mPhotos.get(position);
        holder.mImageView.setContentDescription(photo.getTitle());
        holder.mImageView.setImageResource(getPhotoId(this.mContext, photo.getThumbnail()));

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra(PHOTO_ID, photo.getId());

                if(mContext instanceof Activity) {
                    // start the PhotoActivity and expect a result from an interaction within that activity
                    ((Activity) mContext).startActivityForResult(intent, 1);
                }
            }
        });

        if (photo.getFavourite() == 1) {
            holder.mFab.setVisibility(View.VISIBLE);
        } else {
            holder.mFab.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo)
        ImageView mImageView;

        @BindView(R.id.favourite)
        FloatingActionButton mFab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
