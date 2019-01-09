package com.example.jilson.cryptoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;

final class TickerLoader extends AsyncTaskLoader<Ticker>{

    static final String ACTION = "com.loaders.FORCE";

    // for cashing data when switching between fragments
    private static  Ticker cashedTicker;

    TickerLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter = new IntentFilter(ACTION);
        manager.registerReceiver(broadcastReceiver,filter);

        if(cashedTicker == null){
            forceLoad();
        }else {
            super.deliverResult(cashedTicker);
        }

    }

    @Nullable
    @Override
    public Ticker loadInBackground() {
        return Ticker.fetchTicker();
    }

    @Override
    public void deliverResult(@Nullable Ticker data) {
        cashedTicker = data;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            forceLoad();
        }
    };
}
