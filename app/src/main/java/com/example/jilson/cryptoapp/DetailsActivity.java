package com.example.jilson.cryptoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailsActivity extends AppCompatActivity {
    private Double highestBid;
    private Double lowestAsk;
    private Double lastTradedPrice;
    private Double min24Hrs;
    private Double max24Hrs;
    private Double vol24Hrs;
    private String currencyFullForm;
    private String currencyShortForm;
    private String baseCurrency;
    private Double perChange;
    private Double tradeVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /*gets intent*/
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        /*checks whether bundil is null*/
        if(bundle!= null){
            highestBid = bundle.getDouble("highest_bid");
            lowestAsk = bundle.getDouble("lowest_ask");
            lastTradedPrice = bundle.getDouble("last_traded_price");
            min24Hrs = bundle.getDouble("min_24hrs");
            max24Hrs = bundle.getDouble("max_24hrs");
            vol24Hrs = bundle.getDouble("vol_24hrs");
            currencyFullForm = bundle.getString("currency_full_form");
            currencyShortForm = bundle.getString("currency_short_form");
            baseCurrency = bundle.getString("baseCurrency");
            perChange = bundle.getDouble("per_change");
            tradeVolume = bundle.getDouble("trade_volume");
        }

        //Initializing custom decimal formater to format double values
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##########", decimalFormatSymbols);

        //Each TextView Object is set with vairables
        ((TextView)findViewById(R.id.details_title)).setText(currencyShortForm);
        ((TextView)findViewById(R.id.highest_bid)).setText(decimalFormat.format(highestBid));
        ((TextView)findViewById(R.id.lowest_ask)).setText(decimalFormat.format(lowestAsk));
        ((TextView)findViewById(R.id.last_traded_price)).setText(decimalFormat.format(lastTradedPrice));
        ((TextView)findViewById(R.id.min_24hrs)).setText(decimalFormat.format(min24Hrs));
        ((TextView)findViewById(R.id.max_24hrs)).setText(decimalFormat.format(max24Hrs));
        ((TextView)findViewById(R.id.vol_24hrs)).setText(decimalFormat.format(vol24Hrs));
        ((TextView)findViewById(R.id.currency_full_form)).setText(currencyFullForm);
        ((TextView)findViewById(R.id.currency_short_form)).setText(currencyShortForm);
        ((TextView)findViewById(R.id.baseCurrency)).setText(baseCurrency);
        ((TextView)findViewById(R.id.per_change)).setText(decimalFormat.format(perChange));
        ((TextView)findViewById(R.id.trade_volume)).setText(decimalFormat.format(tradeVolume));
    }
}
