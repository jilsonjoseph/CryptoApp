package com.example.jilson.cryptoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

final class TickerEntryLoader extends AsyncTaskLoader<List<TickerEntry>> {

    public TickerEntryLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<TickerEntry> loadInBackground() {
        Ticker.fetchTicker();
        return null;
    }
}
