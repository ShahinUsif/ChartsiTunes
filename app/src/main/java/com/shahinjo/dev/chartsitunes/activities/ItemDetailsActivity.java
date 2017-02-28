package com.shahinjo.dev.chartsitunes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.utils.SharedPreferencesHelper;

/**
 * Created by shahinjo on 2/27/17.
 */

public class ItemDetailsActivity extends BaseDrawerActivity implements View.OnClickListener {

    public static final String KEY_APP_NAME = "app_name";
    public static final String KEY_APP_URI = "app_uri";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_CONTENT_TYPE = "content_type";
    public static final String KEY_RIGHTS = "rights";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_IMAGE_URI = "image_uri";
    public static final String KEY_IMAGES_LIST = "images_list";

    ViewHolder holder;
    Bundle extras;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View subView = getLayoutInflater().inflate(R.layout.activity_item_details, contentLayout);

        holder = new ViewHolder(subView);

        extras = getIntent().getExtras();

        holder.tvAppName.setText(extras.getString(KEY_APP_NAME));
        holder.tvArtist.setText(extras.getString(KEY_ARTIST));
        holder.tvCategory.setText(extras.getString(KEY_CATEGORY));
        holder.tvContentType.setText(extras.getString(KEY_CONTENT_TYPE));
        holder.tvReleaseDate.setText(extras.getString(KEY_RELEASE_DATE));
        holder.tvRights.setText(extras.getString(KEY_RIGHTS));

        Uri imageUri = Uri.parse(extras.getString(KEY_IMAGE_URI));
        holder.ivImage.setImageURI(imageUri);

        holder.btnDownload.setOnClickListener(this);
        holder.ivImage.setOnClickListener(this);

        sharedPrefs = SharedPreferencesHelper.getInstance(this);

        navigationView.setCheckedItem(sharedPrefs.getNavSelectedItem());

    }

    @Override
    public void onClick(View v) {
        if (v == holder.ivImage) {

            Intent galleryIntent = new Intent(ItemDetailsActivity.this, GalleryActivity.class);

            galleryIntent.putExtra(KEY_IMAGES_LIST, extras.getSerializable(KEY_IMAGES_LIST));

            startActivity(galleryIntent);

        } else if (v == holder.btnDownload) {

            Intent downloadIntent = new Intent(ItemDetailsActivity.this, WebControlActivity.class);

            downloadIntent.putExtra(KEY_APP_URI, extras.getString(KEY_APP_URI));

            startActivity(downloadIntent);
        }
    }

    private class ViewHolder {

        SimpleDraweeView ivImage;
        TextView tvAppName, tvArtist, tvContentType, tvRights, tvCategory, tvReleaseDate;
        Button btnDownload;

        public ViewHolder(View view) {

            ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
            tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
            tvArtist = (TextView) view.findViewById(R.id.tv_artist);
            tvContentType = (TextView) view.findViewById(R.id.tv_content_type);
            tvRights = (TextView) view.findViewById(R.id.tv_rights);
            tvCategory = (TextView) view.findViewById(R.id.tv_category);
            tvReleaseDate = (TextView) view.findViewById(R.id.tv_release_date);

            btnDownload = (Button) view.findViewById(R.id.btn_download);
        }

    }
}
