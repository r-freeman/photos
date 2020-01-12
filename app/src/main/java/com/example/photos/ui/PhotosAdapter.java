package com.example.photos.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photos.PhotoActivity;
import com.example.photos.R;
import com.example.photos.database.PhotoEntity;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PhotoEntity photo = mPhotos.get(position);
        holder.mImageView.setContentDescription(photo.getTitle());
        holder.mImageView.setImageResource(getPhotoId(this.mContext, photo.getThumbnail()));

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Photos", "Photo " + photo.getId() + " was clicked");

                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra(PHOTO_ID, photo.getId());
                mContext.startActivity(intent);
            }
        });
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
