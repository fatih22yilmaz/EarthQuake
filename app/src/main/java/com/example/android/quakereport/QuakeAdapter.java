package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fatih YILMAZ on 6.01.2018.
 */

public class QuakeAdapter extends ArrayAdapter<Quake> {


    public QuakeAdapter(@NonNull Context context, @NonNull ArrayList<Quake> quakes) {
        super(context, 0, quakes);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Quake currentQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeOfQuake = (TextView) listItemView.findViewById(R.id.textview_magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        assert currentQuake != null;
        magnitudeOfQuake.setText("" + currentQuake.getMagnitude());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView labelOfQuake = (TextView) listItemView.findViewById(R.id.textview_label);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        labelOfQuake.setText(currentQuake.getLabel());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateOfQuake = (TextView) listItemView.findViewById(R.id.textview_date);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        dateOfQuake.setText(currentQuake.getDate());


        return listItemView;
    }
}
