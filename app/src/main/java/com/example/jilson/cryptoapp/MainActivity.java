package com.example.jilson.cryptoapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*page viewer object declared and initiliazed*/
        ViewPager viewPager = findViewById(R.id.view_pager);

        /* fragment adapter declared and initialized*/
        SimpleFragmentAdapter simpleFragmentAdapter = new SimpleFragmentAdapter(getSupportFragmentManager(),this);

        /*adapter is set to pageviewer*/
        viewPager.setAdapter(simpleFragmentAdapter);

        /* tab layout object created and initialized */
        TabLayout tabLayout = findViewById(R.id.sliding_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}
