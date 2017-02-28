package com.shahinjo.dev.chartsitunes.utils.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by shahinjo on 2/26/17.
 */

public class GsonProvider {

    private static GsonProvider gsonProvider;
    private Gson gson;

    private GsonProvider() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public static synchronized GsonProvider getInstance() {
        if (gsonProvider == null) {
            gsonProvider = new GsonProvider();
        }
        return gsonProvider;
    }

    public Gson getGson() {
        return gson;
    }
}
