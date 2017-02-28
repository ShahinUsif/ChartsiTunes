package com.shahinjo.dev.chartsitunes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shahinjo.dev.chartsitunes.R;
import com.shahinjo.dev.chartsitunes.utils.SharedPreferencesHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shahinjo on 2/27/17.
 */

public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferencesHelper sharedPrefs;

    /**
     * Views
     */
    View parentView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    LinearLayout contentLayout;
    NavigationView navigationView;

    Integer[] navItemFragments = {R.id.nav_free_apps, R.id.nav_paid_apps, R.id.nav_free_ipad_apps,
            R.id.nav_paid_ipad_apps, R.id.nav_free_books, R.id.nav_about};

    /**
     * Fragments
     */
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parentView = findViewById(R.id.coordinator_parent);
        contentLayout = (LinearLayout) findViewById(R.id.ll_fragment_contents);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        //headerLayout.findViewById(R.id.iv_profile_picture);

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        sharedPrefs = SharedPreferencesHelper.getInstance(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        fragmentManager = getSupportFragmentManager();

        sharedPrefs.storeNavSelectedItem(id);

        if (Arrays.asList(navItemFragments).contains(id)) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            intent.putExtra(MainActivity.KEY_NAV_ITEM_ID, id);

            startActivity(intent);

        } else if (id == R.id.nav_share) {
            actionShareApplication();
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void actionShareApplication() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "iTunes Top Charts");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.shahinjo.dev.chartsitunes \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

