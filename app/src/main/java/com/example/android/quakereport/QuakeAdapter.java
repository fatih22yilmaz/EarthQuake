package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Fatih YILMAZ on 6.01.2018.
 */

public class QuakeAdapter extends ArrayAdapter<Quake> {

    private Date dateObject;
    private SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
    private SimpleDateFormat simpleTime = new SimpleDateFormat("h:mm a", Locale.US);
    private DecimalFormat formatter = new DecimalFormat("0.0");
    private double magnitude;
    private GradientDrawable magnitudeCircle;


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
        magnitude = currentQuake.getMagnitude();
        magnitudeOfQuake.setText(formatter.format(magnitude));

        magnitudeCircle = (GradientDrawable) magnitudeOfQuake.getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView locationUp = (TextView) listItemView.findViewById(R.id.textview_location_up);
        TextView locationDown = (TextView) listItemView.findViewById(R.id.textview_location_down);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView


        locationUp.setText(placeUpString(currentQuake.getPlace()));
        locationDown.setText(placeDownString(currentQuake.getPlace()));

        // Find the TextView in the list_item.xml layout with the ID version_number

        dateObject = new Date(currentQuake.getDate());

        TextView dateOfQuake = (TextView) listItemView.findViewById(R.id.textview_date);
        TextView timeOfQuake = (TextView) listItemView.findViewById(R.id.textview_time);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        dateOfQuake.setText(formatDate(dateObject));
        timeOfQuake.setText(formatTime(dateObject));


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {

        switch ((int) magnitude) {
            case 10:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
        }
    }

    private String formatDate(Date dateObject) {
        return simpleDate.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        return simpleTime.format(dateObject);
    }

    private String placeUpString(String wholeString) {
        if (wholeString.contains(",")) {
            return wholeString.substring(0, wholeString.indexOf("of") + 2);
        } else {
            return "Near the";
        }
    }

    private String placeDownString(String wholeString) {
        if (wholeString.contains(",")) {
            return wholeString.substring(wholeString.indexOf("of") + 3, wholeString.length());
        } else {
            return wholeString.substring(0, wholeString.length());
        }

    }

}
