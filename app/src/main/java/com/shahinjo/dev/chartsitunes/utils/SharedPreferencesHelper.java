package com.shahinjo.dev.chartsitunes.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shahinjo on 2/27/17.
 */

public class SharedPreferencesHelper {

    private static SharedPreferencesHelper prefsHelper;
    private SharedPreferences sharedPrefs;

    public static final String PREFS_ = "prefs.itunes.charts";

    public static final String KEY_VIEW_LAYOUT = "prefs.view_layout";
    public static final String KEY_NAV_SELECTED = "prefs.nav_selected";

    public static final int KEY_LAYOUT_GRID = 0;
    public static final int KEY_LAYOUT_LIST = 1;

    private SharedPreferencesHelper(Context context) {
        sharedPrefs = context.getSharedPreferences(PREFS_, 0);
    }

    public static SharedPreferencesHelper getInstance(Context context) {

        if (prefsHelper == null) {
           prefsHelper = new SharedPreferencesHelper(context);
        }

        return prefsHelper;
    }

    public void storeViewLayout(int viewLayout) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(KEY_VIEW_LAYOUT, viewLayout);
        editor.commit();
    }

    public int getViewLayout() {
        return sharedPrefs.getInt(KEY_VIEW_LAYOUT, KEY_LAYOUT_GRID);
    }

    public void storeNavSelectedItem(int navItemID) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(KEY_NAV_SELECTED, navItemID);
        editor.commit();
    }

    public int getNavSelectedItem() {
        return sharedPrefs.getInt(KEY_NAV_SELECTED, KEY_LAYOUT_GRID);
    }
}
