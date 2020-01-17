package com.example.innoskritproject;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ApplicationFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView tvbrowse,tvbrowsesignature;
    private ImageView calendarimg;
    private CalendarView calender;
    private TextView date_view;
    LinearLayout llBack;
    CardView btnsubmit;
    Intent intent;
    CardView browsefiles;
    CardView browsesignature;
    AlertDialog.Builder builder;
    private RelativeLayout showcalendar;
    private EditText etdate;
    private Spinner spinner;

    public static ApplicationFormFragment newInstance(String param1, String param2) {
        ApplicationFormFragment fragment = new ApplicationFormFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_form, container, false);
        findviews(view);
        return view;
    }

    private void findviews(View view) {
        tvbrowse = view.findViewById(R.id.tvbrowse);
        tvbrowse.setVisibility(View.GONE);
        tvbrowsesignature = view.findViewById(R.id.tvbrowsesignature);
        tvbrowsesignature.setVisibility(View.GONE);
        browsesignature = view.findViewById(R.id.browsesignature);
        browsesignature.setOnClickListener(this);
        calendarimg = view.findViewById(R.id.calendarimg);
        builder = new AlertDialog.Builder(getContext());
        btnsubmit = view.findViewById(R.id.btnsubmit);
        browsefiles = view.findViewById(R.id.browsefiles);
        browsefiles.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        date_view = (TextView) view.findViewById(R.id.date_view);
        showcalendar = view.findViewById(R.id.showcalendar);
        etdate = view.findViewById(R.id.etdate);
        showcalendar.setVisibility(View.GONE);

        calendarimg.setOnClickListener(this);
        calender = (CalendarView) view.findViewById(R.id.calender);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List <String> categories = new ArrayList <String>();
        categories.add("NEPALI");
        categories.add("BHUTIA");
        categories.add("LEPCHA");
        categories.add("LIMBOO");
        categories.add("HINDI");


        // Creating adapter for spinner
        ArrayAdapter <String> dataAdapter = new ArrayAdapter <String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView <?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

        if (v == calendarimg) {
            showcalendar.setVisibility(View.VISIBLE);

            calender
                    .setOnDateChangeListener(
                            new CalendarView
                                    .OnDateChangeListener() {
                                @Override
                                public String toString() {
                                    return "$classname{}";
                                }

                                @Override

                                // In this Listener have one method
                                // and in this method we will
                                // get the value of DAYS, MONTH, YEARS
                                public void onSelectedDayChange(
                                        @NonNull CalendarView view,
                                        int year,
                                        int month,
                                        int dayOfMonth) {

                                    // Store the value of date with
                                    // format in String type Variable
                                    // Add 1 in month because month
                                    // index is start with 0
                                    String Date
                                            = dayOfMonth + "/"
                                            + (month + 1) + "/" + year;

                                    // set this date in TextView for Display
                                    date_view.setText(Date);
                                    etdate.setText(Date);
                                    showcalendar.setVisibility(View.GONE);
                                }
                            });


        }

        if (v == btnsubmit) {
            builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

            //Setting message manually and performing action on button click
            builder.setMessage("Do you want to submit this application ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(getContext(), "Thank You!!! Your application is submitted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Toast.makeText(getContext(), "You choose not to submit your application",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

            AlertDialog alert = builder.create();

            alert.setTitle("Submit Application");
            alert.show();
        }
        if (v == browsefiles) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 7);

        }
        if(v==browsesignature){
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 8);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub

        switch (requestCode) {

            case 7:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();

                    Toast.makeText(getContext(), PathHolder, Toast.LENGTH_LONG).show();
                    tvbrowse.setVisibility(View.VISIBLE);
                    tvbrowse.setText(PathHolder);

                }
                break;

            case 8:
                if (resultCode == RESULT_OK) {

                    String PathHolder1 = data.getData().getPath();

                    Toast.makeText(getContext(), PathHolder1, Toast.LENGTH_LONG).show();
                    tvbrowsesignature.setVisibility(View.VISIBLE);
                    tvbrowsesignature.setText(PathHolder1);

                }
                break;

        }


    }

}



