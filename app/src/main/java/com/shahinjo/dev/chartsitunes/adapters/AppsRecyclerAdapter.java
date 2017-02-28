package com.shahinjo.dev.chartsitunes.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.activities.ItemDetailsActivity;
import com.shahinjo.dev.chartsitunes.models.store_apps.Entry;
import com.shahinjo.dev.chartsitunes.models.store_apps.ImImage;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/26/17.
 */

public class AppsRecyclerAdapter extends RecyclerView.Adapter<AppsRecyclerAdapter.ViewHolder> {

    /**
     * Context
     */
    Context context;

    /**
     * Variables
     */
    ArrayList<Entry> appData;
    int layoutID;

    public AppsRecyclerAdapter(Context context, ArrayList<Entry> galleryData, int layoutID) {
        this.appData = galleryData;
        this.context = context;
        this.layoutID = layoutID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Entry currentEntry = appData.get(position);

        holder.tvAppTitle.setText(currentEntry.getImName().getLabel());
        holder.tvAppCategory.setText(currentEntry.getCategory().getAttributes().getTerm());

        Uri imageUri = Uri.parse(currentEntry.getImImage().get(2).getLabel());
        holder.ivImage.setImageURI(imageUri);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailsIntent = new Intent(context, ItemDetailsActivity.class);

                detailsIntent.putExtra(ItemDetailsActivity.KEY_APP_NAME, currentEntry.getImName().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_ARTIST, currentEntry.getImArtist().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_CATEGORY, currentEntry.getCategory().getAttributes().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_CONTENT_TYPE, currentEntry.getImContentType().getAttributes().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_RIGHTS, currentEntry.getRights().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_RELEASE_DATE, currentEntry.getImReleaseDate().getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_IMAGE_URI, currentEntry.getImImage().get(2).getLabel());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_APP_URI, currentEntry.getLink().getAttributes().getHref());

                ArrayList<ImImage> imagesList = new ArrayList<ImImage>(currentEntry.getImImage());
                detailsIntent.putExtra(ItemDetailsActivity.KEY_IMAGES_LIST, imagesList);

                currentEntry.getTitle();
                context.startActivity(detailsIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return appData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvAppTitle, tvAppCategory;
        private SimpleDraweeView ivImage;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview);
            tvAppTitle = (TextView) view.findViewById(R.id.tv_app_name);
            tvAppCategory = (TextView) view.findViewById(R.id.tv_app_category);
            ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
        }
    }

}
