package com.shahinjo.dev.chartsitunes.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shahinjo.dev.chartsitunes.activities.ImageViewerActivity;
import com.shahinjo.dev.chartsitunes.fragments.ImageViewerFragment;
import com.shahinjo.dev.chartsitunes.models.store_apps.ImImage;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/28/17.
 */

public class GalleryPagerAdapter extends FragmentStatePagerAdapter {

    ImageViewerFragment viewerFragment;

    private ArrayList<ImImage> imagesList;

    public GalleryPagerAdapter(FragmentManager fm, ArrayList<ImImage> imagesList) {
        super(fm);
        this.imagesList = imagesList;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        viewerFragment = new ImageViewerFragment();

        bundle.putString(ImageViewerActivity.KEY_IMAGE_URI, imagesList.get(position).getLabel());

        viewerFragment.setArguments(bundle);
        return viewerFragment;
    }


    @Override
    public int getCount() {
        return imagesList.size();
    }

}
