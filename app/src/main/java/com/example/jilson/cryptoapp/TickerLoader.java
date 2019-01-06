package com.example.jilson.cryptoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

final class TickerLoader extends AsyncTaskLoader<Ticker> {

    public TickerLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public Ticker loadInBackground() {
        return Ticker.fetchTicker();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }
}
