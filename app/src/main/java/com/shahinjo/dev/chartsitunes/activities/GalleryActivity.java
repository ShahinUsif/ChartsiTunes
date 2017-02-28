package com.shahinjo.dev.chartsitunes.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.adapters.ImagesRecyclerAdapter;
import com.shahinjo.dev.chartsitunes.models.store_apps.ImImage;
import com.shahinjo.dev.chartsitunes.utils.SharedPreferencesHelper;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/27/17.
 */

public class GalleryActivity extends BaseDrawerActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImagesRecyclerAdapter adapter;
    ArrayList<ImImage> imagesList;
    private SwipeRefreshLayout swipeContainer;

    Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View subView = getLayoutInflater().inflate(R.layout.activity_gallery, contentLayout);

        extras = getIntent().getExtras();
        imagesList = (ArrayList<ImImage>) extras.getSerializable(ItemDetailsActivity.KEY_IMAGES_LIST);

        swipeContainer = (SwipeRefreshLayout) subView.findViewById(R.id.swipe_container);
        swipeContainer.setOnRefreshListener(this);

        recyclerView = (RecyclerView) subView.findViewById(R.id.image_gallery);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        sharedPrefs = SharedPreferencesHelper.getInstance(this);

        navigationView.setCheckedItem(sharedPrefs.getNavSelectedItem());

        fillData();
    }

    private void fillData() {
        swipeContainer.setRefreshing(true);
        if (imagesList != null) {

            adapter = new ImagesRecyclerAdapter(this, imagesList);
            recyclerView.setAdapter(adapter);

        }
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

        fillData();

    }
}
