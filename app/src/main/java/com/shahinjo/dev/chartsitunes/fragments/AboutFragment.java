package com.shahinjo.dev.chartsitunes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shahinjo.dev.chartsitunes.R;

/**
 * Created by shahinjo on 2/26/17.
 */

public class AboutFragment extends Fragment {

    public static final String KEY_NAME = "name";
    public static final String KEY_UPDATED = "updated";
    public static final String KEY_RIGHTS = "rights";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);


        return rootView;
    }
}
