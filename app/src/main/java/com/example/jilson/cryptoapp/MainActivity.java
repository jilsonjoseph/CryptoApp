package com.example.jilson.cryptoapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.Timestamp;


public class MainActivity extends AppCompatActivity  {

    // in milliseconds
    private static final long apiCallFrequency = 60000;

    private static boolean threadStarted;

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

        //ToDo: remove log
        Log.v("Main","[][][][][][][][[][[][][][]][][][][][][][][][][[][][][ in MainActivity onCreate");

        // if condition to avoid thread being created each time MainActivity is restarted
        if(!threadStarted){
            threadStarted = true;
            // Start the initial runnable task by posting through the handler
            handler.post(runnableCode);
        }
    }

    // Create the Handler object (on the main thread by default)
    private Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Repeat this the same runnable code block again every 60 seconds
            handler.postDelayed(runnableCode, apiCallFrequency);

            //Initiates reload of forceLoad() in TickerLoader
            reload();
        }
    };

    void reload(){
        //Todo:remove log
        Log.v("Reload","[][][][][][][][][][][][][] reload in MainActivity");
        Intent intent = new Intent(TickerLoader.ACTION);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
    }

}
