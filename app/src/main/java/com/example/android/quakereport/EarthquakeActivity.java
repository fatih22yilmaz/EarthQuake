/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 0;
    private static QuakeAdapter adapter;
    private TextView noEarthquakeTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        assert earthquakeListView != null;
        earthquakeListView.setEmptyView(findViewById(R.id.empty));

        noEarthquakeTextView = (TextView) findViewById(R.id.empty);
        assert noEarthquakeTextView != null;
        noEarthquakeTextView.setText("");

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Quake currentEarthquake = adapter.getItem(i);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                websiteIntent.setData(Uri.parse(currentEarthquake != null ? currentEarthquake.getUrl() : null));
                startActivity(websiteIntent);
            }
        });

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new QuakeAdapter(this, new ArrayList<Quake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {

            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
            Log.v(LOG_TAG, "Init Loader.");

        } else {
            progressBar.setVisibility(View.GONE);
            noEarthquakeTextView.setText(R.string.no_internet);
        }


    }

    @Override
    public Loader<List<Quake>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "onCreateLoader.");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Quake>> loader, List<Quake> quakes) {
        Log.v(LOG_TAG, "onLoadFinished.");
        // Clear the adapter of previous earthquake data


        progressBar.setVisibility(View.GONE);

        noEarthquakeTextView.setText(R.string.no_earthquake);
        adapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (quakes != null && !quakes.isEmpty()) {
            adapter.addAll(quakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Quake>> loader) {
        Log.v(LOG_TAG, "onLoaderReset.");
        adapter.clear();
    }

}
