package com.shahinjo.dev.chartsitunes;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by shahinjo on 2/26/17.
 */

public class ITunesChartsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
