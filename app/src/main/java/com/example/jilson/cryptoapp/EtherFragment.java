package com.example.jilson.cryptoapp;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EtherFragment extends Fragment implements LoaderManager.LoaderCallbacks<Ticker> {

    private TickerEntryAdapter adapter;
    private List<TickerEntry> etherList = new ArrayList<>();

    // in milliseconds
    private static final long apiCallFrequency = 60000;

    private static final String LOG_TAG = InrFragment.class.getName();
    private View rootView;

    public EtherFragment() {
        // Required empty public constructor
    }

    // Create the Handler object (on the main thread by default)
    Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {

            // Repeat this the same runnable code block again every 60 seconds
            handler.postDelayed(runnableCode, apiCallFrequency);

            //Initiate the loader
            loaderInitiator();
        }
    };

    void loaderInitiator(){
        // Using Loader to fetch data in a bockground thread
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(2,null,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*initialising rootview*/
        rootView = inflater.inflate(R.layout.list,container,false);

        if(connected()){
            /*declare and initilize new adapter*/
            adapter = new TickerEntryAdapter(getActivity(),new ArrayList<TickerEntry>());

            /*list view is initiliased and adapter is set to listview*/
            ListView listView = rootView.findViewById(R.id.list);
            listView.setAdapter(adapter);

            /*onitemclick listener is set to listview*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    TickerEntry tickerEntry = etherList.get(i);
                    Details details = tickerEntry.getDetails();

                    //intent to start new activity and data is also bassed to the new activity
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("highest_bid",details.getHighestBid());
                    bundle.putDouble("lowest_ask",details.getLowestAsk());
                    bundle.putDouble("last_traded_price",details.getLastTradedPrice());
                    bundle.putDouble("min_24hrs",details.getMin24Hrs());
                    bundle.putDouble("max_24hrs",details.getMax24Hrs());
                    bundle.putDouble("vol_24hrs",details.getVol24Hrs());
                    bundle.putString("currency_full_form",details.getCurrencyFullForm());
                    bundle.putString("currency_short_form",details.getCurrencyShortForm());
                    bundle.putString("baseCurrency",details.getBaseCurrency());
                    bundle.putDouble("per_change",details.getPerChange());
                    bundle.putDouble("trade_volume",details.getTradeVolume());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(2,null,this);

            // Start the initial runnable task by posting through the handler
            handler.post(runnableCode);

        }else {

            // No internet hence no progress bar required so its hidden
            ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            //  empty text is set to listview to a display message if no news entries found
            ListView listView = rootView.findViewById(R.id.list);
            TextView emptyTextView = rootView.findViewById(R.id.empty_view);
            emptyTextView.setText(R.string.no_internet);
            listView.setEmptyView(emptyTextView);
        }
        return rootView;
    }

    private void updateUi(){
        // as data loading is finished no need to show progress bar
        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public Loader<Ticker> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new TickerLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Ticker> loader, Ticker ticker) {
        this.etherList = ticker.getEtherList();
        adapter.clear();
        adapter.addAll(etherList);
        adapter.notifyDataSetChanged();
        updateUi();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Ticker> loader) {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.destroyLoader(2);
    }

    // Helper method to access network state
    private boolean connected(){
        Context context = getContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
