package com.example.jilson.cryptoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

final class TickerEntryAdapter extends ArrayAdapter<TickerEntry> {

    public TickerEntryAdapter(@NonNull Context context, @NonNull List<TickerEntry> objects) {
        super(context, 0,objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,  @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TickerEntry tickerEntry = getItem(position);

        /*text view object created and ticker symbol set*/
        TextView symbolTextView = listItemView.findViewById(R.id.symbol);
        symbolTextView.setText(tickerEntry.getSymbol());

        /*text view object created and ticker price set*/
        TextView priceTextView = listItemView.findViewById(R.id.value);
        priceTextView.setText(tickerEntry.getPrice().toString());

        return listItemView;
    }
}
