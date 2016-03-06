package com.cs407.tylerphelps.cs407hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.FragmentTransaction;

public class DateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Intent intent = getIntent();

        int month = intent.getIntExtra("month", 0) + 1;
        int day = intent.getIntExtra("day", 0);
        int year = intent.getIntExtra("year", 0);

        setTitle(month + "/" + day + "/" + year);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.date_fragment_container, DateFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }
}
