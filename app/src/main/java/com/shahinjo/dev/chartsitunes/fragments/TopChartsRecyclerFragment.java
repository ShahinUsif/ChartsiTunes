package com.shahinjo.dev.chartsitunes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.adapters.AppsRecyclerAdapter;
import com.shahinjo.dev.chartsitunes.models.store_apps.Entry;
import com.shahinjo.dev.chartsitunes.models.store_apps.TopAppsScheme;
import com.shahinjo.dev.chartsitunes.utils.SharedPreferencesHelper;
import com.shahinjo.dev.chartsitunes.utils.endpoint.EndPointClient;
import com.shahinjo.dev.chartsitunes.utils.endpoint.GsonProvider;

import java.util.ArrayList;

/**
 * Created by shahinjo on 2/26/17.
 */

public class TopChartsRecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    public static final String KEY_TITLE = "";

    Context context;
    SharedPreferencesHelper sharedPrefs;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeContainer;
    ToggleButton tbLayout;
    TextView tvTitle;

    ArrayList<Entry> contentsList;

    private String ENDPOINT_URL;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_charts, container, false);

        this.context = rootView.getContext();
        sharedPrefs = SharedPreferencesHelper.getInstance(context);

        ENDPOINT_URL = getArguments().getString(EndPointClient.KEY_ENDPOINT_URL);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeContainer.setOnRefreshListener(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.image_gallery);
        recyclerView.setHasFixedSize(true);

        tbLayout = (ToggleButton) rootView.findViewById(R.id.tb_layout);
        tbLayout.setOnClickListener(this);

        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvTitle.setText(getArguments().getInt(KEY_TITLE));

        int viewLayout = sharedPrefs.getViewLayout();
        if(viewLayout == SharedPreferencesHelper.KEY_LAYOUT_LIST) {
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            tbLayout.setChecked(true);
        } else  {
            layoutManager = new GridLayoutManager(context, 2);
            recyclerView.setLayoutManager(layoutManager);
            tbLayout.setChecked(false);
        }


        retrieveGalleryData();

        return rootView;
    }

    private void fillData() {
        if (contentsList != null) {

            AppsRecyclerAdapter adapter;

            if (tbLayout.isChecked())
                adapter = new AppsRecyclerAdapter(context, contentsList, R.layout.row_item_summary);
            else
                adapter = new AppsRecyclerAdapter(context, contentsList, R.layout.cell_item_summary);

            recyclerView.setAdapter(adapter);
        }

        swipeContainer.setRefreshing(false);
    }

    private void retrieveGalleryData() {

        swipeContainer.setRefreshing(true);
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT_URL, onPostsLoaded, onPostsError);
        EndPointClient.getInstance(context).addToRequestQueue(request);

    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            // TODO Change to adapt for Music, Movies, ...
            TopAppsScheme topAppsScheme = GsonProvider.getInstance().getGson().fromJson(response, TopAppsScheme.class);

            contentsList = new ArrayList<>();
            contentsList.addAll(topAppsScheme.getFeed().getEntry());

            fillData();

            Log.i("PostActivity", topAppsScheme.getFeed().getEntry().size() + " posts loaded.");

            swipeContainer.setRefreshing(false);
        }

    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            contentsList = new ArrayList<>();

            fillData();
            Log.e("MainActivity", error.toString());

            swipeContainer.setRefreshing(false);
        }
    };

    @Override
    public void onRefresh() {
        retrieveGalleryData();
    }

    @Override
    public void onClick(View v) {
        if(v == tbLayout) {
            if (tbLayout.isChecked()) {
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                sharedPrefs.storeViewLayout(SharedPreferencesHelper.KEY_LAYOUT_LIST);
            } else {
                layoutManager = new GridLayoutManager(context, 2);
                recyclerView.setLayoutManager(layoutManager);
                sharedPrefs.storeViewLayout(SharedPreferencesHelper.KEY_LAYOUT_GRID);
            }

            fillData();
        }
    }
}
