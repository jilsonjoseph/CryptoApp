package com.example.jilson.cryptoapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class inrFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*initialising rootview*/
        View rootView = inflater.inflate(R.layout.list,container,false);

        /*initializing array list of objects*/
        final List<TickerEntry> tickerEntries =new ArrayList<TickerEntry>();
        TickerEntry t = new TickerEntry();
        t.setSymbol("ETH");
        t.setPrice(1080.0);
        tickerEntries.add(t);

        /*declare and initilize new adapter*/
        TickerEntryAdapter adapter = new TickerEntryAdapter(getActivity(),tickerEntries);

        /*list view is initiliased and adapter is set to listview*/
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        /*onitemclick listener is set to listview*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*intent to start new activity and data is also bassed to the new activity*//*
                //Intent intent = new Intent(getActivity(),DetailsActivity.class);
                //Bundle bundle = new Bundle();
                //Attraction attraction = attractions.get(i);
                //bundle.putInt("image_id",attraction.getAttractionImageResourceId());
                bundle.putString("text_id",attraction.getAttractionDescription());
                bundle.putString("title_id",attraction.getAttractionPlaceName());
                intent.putExtras(bundle);
                startActivity(intent);*/

            }
        });


        return rootView;
    }

}
