package com.example.photos.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photos.R;
import com.example.photos.model.PhotoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final List<PhotoEntity> mPhotos;
    private final Context mContext;

    public PhotosAdapter(List<PhotoEntity> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    public static int getPhotoId(Context mContext, String photoFileName) {
        return mContext.getResources().getIdentifier("drawable/" + photoFileName, null, mContext.getPackageName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PhotoEntity photo = mPhotos.get(position);
        holder.mImageView.setContentDescription(photo.getTitle());
        holder.mImageView.setImageResource(getPhotoId(this.mContext, photo.getFileName()));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo)
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
