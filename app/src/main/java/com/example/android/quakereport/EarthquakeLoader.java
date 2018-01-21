package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Fatih YILMAZ on 21.01.2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Quake>> {
    public static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String url;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "onStartLoading.");
        forceLoad();
    }

    @Override
    public List<Quake> loadInBackground() {
        Log.v(LOG_TAG, "loadInBackground.");
        if (url == null) {
            return null;
        }

        return QueryUtils.fetchEarthquakeData(url);
    }
}