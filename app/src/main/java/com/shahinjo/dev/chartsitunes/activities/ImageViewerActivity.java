package com.shahinjo.dev.chartsitunes.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.adapters.GalleryPagerAdapter;
import com.shahinjo.dev.chartsitunes.models.store_apps.ImImage;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/27/17.
 */

public class ImageViewerActivity extends AppCompatActivity {

    public static final String KEY_IMAGE_INDEX = "image_index";
    public static final String KEY_IMAGE_URI = "image_uri";
    public static final String KEY_IMAGES_LIST = "images_list";

    Bundle extras;
    ViewPager viewPager;
    GalleryPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer_pager);

        extras = getIntent().getExtras();

        ArrayList<ImImage> imagesList = (ArrayList<ImImage>) extras.getSerializable(ImageViewerActivity.KEY_IMAGES_LIST);
        int imageIndex = extras.getInt(KEY_IMAGE_INDEX);

        viewPager = (ViewPager) findViewById(R.id.images_pager);
        adapter = new GalleryPagerAdapter(getSupportFragmentManager(), imagesList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imageIndex);
    }


}
