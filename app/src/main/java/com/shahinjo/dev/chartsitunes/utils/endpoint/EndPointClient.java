package com.shahinjo.dev.chartsitunes.utils.endpoint;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shahinjo on 2/26/17.
 */

public class EndPointClient {

    /**
     * Keys
     */

    public static final String KEY_ENDPOINT_URL = "endpoint_url";

    /**
     * URLS
     */

    private static final String URL_BASE = "https://itunes.apple.com/jo/rss/";

    //    private static final String URL_TOP_ = "";
    public static final String URL_TOP_FREE_APPS = "topfreeapplications";
    public static final String URL_TOP_PAID_APPS = "toppaidapplications";
    public static final String URL_TOP_FREE_IPAD_APPS = "topfreeipadapplications";
    public static final String URL_TOP_PAID_IPAD_APPS = "toppaidipadapplications";

    public static final String URL_TOP_FREE_BOOKS = "topfreeebooks";


    private static final String URL_SUFFIX = "/limit=50/json";

    /**
     * Singleton Members
     */
    private static EndPointClient endPointClient;
    private RequestQueue requestQueue;
    private static Context context;


    private EndPointClient(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static synchronized EndPointClient getInstance(Context context) {
        if (endPointClient == null) {
            endPointClient = new EndPointClient(context);
        }
        return endPointClient;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static String getRequestURL(String operation) {
        return URL_BASE + operation + URL_SUFFIX;
    }
}
