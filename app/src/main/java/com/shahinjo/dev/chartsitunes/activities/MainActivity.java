package com.shahinjo.dev.chartsitunes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.fragments.AboutFragment;
import com.shahinjo.dev.chartsitunes.fragments.TopChartsRecyclerFragment;
import com.shahinjo.dev.chartsitunes.utils.endpoint.EndPointClient;

public class MainActivity extends BaseDrawerActivity {

    /**
     * Keys
     */
    public static final String KEY_NAV_ITEM_ID = "nav_item_id";

    public static final int KEY_TOP_FREE_APPS = 1;
    public static final int KEY_TOP_PAID_APPS = 2;
    public static final int KEY_TOP_FREE_IPAD_APPS = 3;
    public static final int KEY_TOP_PAID_IPAD_APPS = 4;

    public static final int KEY_TOP_FREE_BOOKS = 5;

    /**
     * Variables
     */
    private int navID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navID = getIntent().getIntExtra(KEY_NAV_ITEM_ID, R.id.nav_about);

        navigateFragments();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);

        navID = intent.getIntExtra(KEY_NAV_ITEM_ID, R.id.nav_about);
        navigateFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean navigateFragments() {

        Fragment fragment = null;
        Bundle bundle = new Bundle();

        if (navID == R.id.nav_free_apps) {

            fragment = new TopChartsRecyclerFragment();
            bundle.putString(EndPointClient.KEY_ENDPOINT_URL, EndPointClient.getRequestURL(EndPointClient.URL_TOP_FREE_APPS));
            bundle.putInt(TopChartsRecyclerFragment.KEY_TITLE, R.string.title_free_apps);
            fragment.setArguments(bundle);

        } else if (navID == R.id.nav_paid_apps) {

            fragment = new TopChartsRecyclerFragment();
            bundle.putString(EndPointClient.KEY_ENDPOINT_URL, EndPointClient.getRequestURL(EndPointClient.URL_TOP_PAID_APPS));
            bundle.putInt(TopChartsRecyclerFragment.KEY_TITLE, R.string.title_paid_apps);
            fragment.setArguments(bundle);

        } else if (navID == R.id.nav_free_ipad_apps) {

            fragment = new TopChartsRecyclerFragment();
            bundle.putString(EndPointClient.KEY_ENDPOINT_URL, EndPointClient.getRequestURL(EndPointClient.URL_TOP_FREE_IPAD_APPS));
            bundle.putInt(TopChartsRecyclerFragment.KEY_TITLE, R.string.title_ipad_free_apps);
            fragment.setArguments(bundle);

        } else if (navID == R.id.nav_paid_ipad_apps) {

            fragment = new TopChartsRecyclerFragment();
            bundle.putString(EndPointClient.KEY_ENDPOINT_URL, EndPointClient.getRequestURL(EndPointClient.URL_TOP_PAID_IPAD_APPS));
            bundle.putInt(TopChartsRecyclerFragment.KEY_TITLE, R.string.title_ipad_paid_apps);
            fragment.setArguments(bundle);

        } else if (navID == R.id.nav_free_books) {

            fragment = new TopChartsRecyclerFragment();
            bundle.putString(EndPointClient.KEY_ENDPOINT_URL, EndPointClient.getRequestURL(EndPointClient.URL_TOP_FREE_BOOKS));
            bundle.putInt(TopChartsRecyclerFragment.KEY_TITLE, R.string.title_free_books);
            fragment.setArguments(bundle);

        } else if (navID == R.id.nav_about) {
            fragment = new AboutFragment();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.ll_fragment_contents, fragment).commit();
            navigationView.setCheckedItem(navID);
        }

        return true;
    }


}
