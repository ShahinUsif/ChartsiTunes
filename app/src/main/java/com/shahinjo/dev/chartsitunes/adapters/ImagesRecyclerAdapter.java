package com.shahinjo.dev.chartsitunes.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.activities.ImageViewerActivity;
import com.shahinjo.dev.chartsitunes.models.store_apps.ImImage;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/27/17.
 */

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder> {

    /**
     * Context
     */
    Context context;

    /**
     * Variables
     */
    ArrayList<ImImage> galleryData;

    public ImagesRecyclerAdapter(Context context, ArrayList<ImImage> galleryData) {
        this.galleryData = galleryData;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_gallery_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Uri imageUri = Uri.parse(galleryData.get(position).getLabel());
        final int mPosition = position;
        holder.ivImage.setImageURI(imageUri);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent();
                intent.setDataAndType(imageUri, "image*//*");
                context.startActivity(intent);*/

                Intent intent = new Intent(context, ImageViewerActivity.class);
                intent.putExtra(ImageViewerActivity.KEY_IMAGE_INDEX, position);
                intent.putExtra(ImageViewerActivity.KEY_IMAGE_URI, imageUri.toString());
                intent.putExtra(ImageViewerActivity.KEY_IMAGES_LIST, galleryData);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView ivImage;

        public ViewHolder(View view) {
            super(view);
            ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
        }
    }

}
