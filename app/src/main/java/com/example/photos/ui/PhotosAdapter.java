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

/**
 * The PhotosAdapter class inherits from ViewHolder which defines how each element
 * in the RecyclerView looks and behaves.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final List<PhotoEntity> mPhotos;
    private final Context mContext;

    // constructor
    public PhotosAdapter(List<PhotoEntity> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    /**
     * Helper method for converting a filename to a resource id
     *
     * @param mContext
     * @param photoFileName
     * @return
     */
    public static int getPhotoId(Context mContext, String photoFileName) {
        return mContext.getResources().getIdentifier(DRAWABLE_PATH +
                photoFileName, null, mContext.getPackageName());
    }

    /**
     * Method runs when an instance of the ViewHolder class is created.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // "inflate" each item in the RecyclerView with our item_photo layout
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Method runs when the ViewHolder is bound to its container. Here we can set
     * the photo thumbnail and behaviour when the photo is clicked
     *
     * @param holder
     * @param position
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // position in the RecyclerView relates to the PhotoEntity id attribute
        final PhotoEntity photo = mPhotos.get(position);

        // setting the content description of the Photo component
        holder.mImageView.setContentDescription(photo.getTitle());
        // changing the ImageView src to the photo thumbnail
        holder.mImageView.setImageResource(getPhotoId(this.mContext, photo.getThumbnail()));

        // set a click event listener to the photo
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we'll be sending the photo id to the PhotoActivity
                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra(PHOTO_ID, photo.getId());

                if (mContext instanceof Activity) {
                    // start the PhotoActivity and expect a result back from the PhotoActivity
                    // if a photo is deleted in PhotoActivity we want to display a Snackbar message in the MainActivity
                    // indicating that the photo was deleted.
                    ((Activity) mContext).startActivityForResult(intent, 1);
                }
            }
        });

        // sets the visibility of the heart icon in the bottom right corner of the photo
        // if the photo is a favourite then the icon is visible and vice versa.
        if (photo.getFavourite() == 1) {
            holder.mFab.setVisibility(View.VISIBLE);
        } else {
            holder.mFab.setVisibility(View.GONE);
        }
    }

    // returns the count
    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    /**
     * ViewHolder class each item in the RecyclerView will be an instance of this class.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // binding ImageView and Fab components to the ViewHolder with Butterknife this will allow us to
        // access these components programmatically in onBindViewHolder (above).
        @BindView(R.id.photo)
        ImageView mImageView;

        @BindView(R.id.favourite)
        FloatingActionButton mFab;

        // constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // standard Butterknife binding
            ButterKnife.bind(this, itemView);
        }
    }
}
