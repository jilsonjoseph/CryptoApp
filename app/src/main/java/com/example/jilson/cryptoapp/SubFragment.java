package com.example.jilson.cryptoapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class SubFragment extends Fragment implements LoaderManager.LoaderCallbacks<Ticker> {

    private TickerEntryAdapter adapter = null;
    private List<TickerEntry> currencyList = new ArrayList<>();
    private int fragmentId;

    private static final String LOG_TAG = SubFragment.class.getSimpleName();
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getting the id of fragment
        fragmentId = getArguments().getInt("fragment_id");
        /*initialising rootview*/
        rootView = inflater.inflate(R.layout.list,container,false);

        //Fetch data only if Internet is connected
        if(connected()){
            /*list view is initiliased and adapter is set to listview*/
            ListView listView = rootView.findViewById(R.id.list);
            adapter = new TickerEntryAdapter(getActivity(),new ArrayList<TickerEntry>());
            listView.setAdapter(adapter);

            /*onitemclick listener is set to listview*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    TickerEntry tickerEntry = currencyList.get(i);
                    Details details = tickerEntry.getDetails();

                    //intent to start new activity and data is also passed to the new activity
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
            loaderManager.initLoader(0,null,this);

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

    @Override
    public Loader<Ticker> onCreateLoader(int i, Bundle bundle) {

        return new TickerLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Ticker> loader, Ticker ticker) {

        // currencyList is populated according to the fragmentId
        switch (fragmentId){
            case 0:
                this.currencyList = ticker.getInrList();
                break;
            case 1:
                this.currencyList = ticker.getBitcoinList();
                break;
            case 2:
                this.currencyList = ticker.getEtherList();
                break;
            case 3:
                this.currencyList = ticker.getRippleList();
        }
        adapter.clear();
        adapter.addAll(currencyList);
        adapter.notifyDataSetChanged();
        updateUi();
        //ToDo: remove log
        Log.v(LOG_TAG,"in  onLoadfinished ###################################### Fragment Id:"+fragmentId);
    }

    @Override
    public void onLoaderReset(Loader<Ticker> loader) {

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
