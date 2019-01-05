package com.example.jilson.cryptoapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

final class Ticker {

    // Tag for Log
    private static final String LOG_TAG = Ticker.class.getSimpleName();

    private static final String TICKER_REQUEST_URL = "https://koinex.in/api/ticker";

    private static final String inrKeys[] = {"ETH", "BTC", "LTC", "XRP", "OMG", "REQ", "ZRX", "GNT",
            "BAT", "AE", "TRX", "XLM", "NEO", "GAS", "XRB", "NCASH", "EOS", "CMT", "ONT", "ZIL",
            "IOST", "ACT", "ZCO", "SNT", "POLY", "ELF", "REP", "QKC", "XZC", "BCHABC",
            "TUSD", "BCHSV"};

    private static final String bitcoinKeys[] = {"TUSD", "LTC", "NCASH", "XRP", "OMG", "EOS",
            "REQ", "ETH", "ZCO", "TRX", "BCHABC"};

    private static final String etherKeys[] = {"XRP", "TRX", "TUSD", "LTC", "OMG", "EOS", "ZCO",
            "BCHABC"};

    private static final String rippleKeys[] ={"CMT", "LTC", "NCASH", "AE", "EOS", "GNT", "REQ", "OMG", "ONT",
            "ZIL", "IOST", "ACT", "ZCO", "SNT", "POLY", "ELF", "TRX", "REP", "QKC", "XZC",
            "TUSD"};

    private static List<TickerEntry> inrList = new ArrayList<TickerEntry>();
    private static List<TickerEntry> bitcoinList = new ArrayList<TickerEntry>();
    private static List<TickerEntry> etherList = new ArrayList<TickerEntry>();
    private static List<TickerEntry> rippleList = new ArrayList<TickerEntry>();

    static void fetchTicker(){
        String jsonResponse = HttpRequest.fetchData(TICKER_REQUEST_URL);
        extractDataFromJSON(jsonResponse);
    }

    private static void extractDataFromJSON(String jsonResponse) {
        Log.v(LOG_TAG," in extractDataFromJSON method");

        // Parsing JSON Response.
        try {
            JSONObject response = new JSONObject(jsonResponse);

            // JSON Objects for prices
            JSONObject inrPrices = null;
            JSONObject bitcoinPrices = null;
            JSONObject etherPrices = null;
            JSONObject ripplePrices = null;

            if(response.has("prices")) {
                JSONObject prices = response.getJSONObject("prices");

                if (prices.has("inr"))
                    inrPrices = prices.getJSONObject("inr");
                if (prices.has("bitcoin"))
                    bitcoinPrices = prices.getJSONObject("bitcoin");
                if (prices.has("ether"))
                    etherPrices = prices.getJSONObject("ether");
                if (prices.has("ripple"))
                    ripplePrices = prices.getJSONObject("ripple");
            }

            // JSON Object for stats
            JSONObject inrStats = null;
            JSONObject bitcoinStats = null;
            JSONObject etherStats = null;
            JSONObject rippleStats = null;

            if(response.has("stats")) {
                JSONObject stats = response.getJSONObject("stats");

                if (stats.has("inr"))
                    inrStats = stats.getJSONObject("inr");
                if (stats.has("bitcoin"))
                    bitcoinStats = stats.getJSONObject("bitcoin");
                if (stats.has("ether"))
                    etherStats = stats.getJSONObject("ether");
                if (stats.has("ripple"))
                    rippleStats = stats.getJSONObject("ripple");
            }

                listSetter(inrList,inrKeys,inrPrices,inrStats);
                listSetter(bitcoinList,bitcoinKeys,bitcoinPrices,bitcoinStats);
                listSetter(etherList,etherKeys,etherPrices,etherStats);
                listSetter(rippleList,rippleKeys,ripplePrices,rippleStats);

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("Ticker", "Error in parsing JSON results", e);
        }
    }

    /**
     * Helper method to get all the details in stats from each cryptocurrency JSON Object
     * and returns a Details Object with all entries from the JSON object added
     * @param details accepts JSON object in stats
     * @return returns Details object **/
    private static Details getDetails(JSONObject details)throws JSONException{

        Details detailsObject = new Details();

        if(details.has("highest_bid")){
            detailsObject.setHighestBid(details.getDouble("highest_bid"));
        }

        if(details.has("lowest_ask")){
            detailsObject.setLowestAsk(details.getDouble("lowest_ask"));
        }

        if(details.has("last_traded_price")){
            detailsObject.setLastTradedPrice(details.getDouble("last_traded_price"));
        }

        if(details.has("min_24hrs")){
            detailsObject.setMin24Hrs(details.getDouble("min_24hrs"));
        }

        if(details.has("max_24hrs")){
            detailsObject.setMax24Hrs(details.getDouble("max_24hrs"));
        }

        if(details.has("vol_24hrs")){
            detailsObject.setVol24Hrs(details.getDouble("vol_24hrs"));
        }

        if(details.has("currency_full_form")){
            detailsObject.setCurrencyFullForm(details.getString("currency_full_form"));
        }

        if(details.has("currency_short_form")){
            detailsObject.setCurrencyShortForm(details.getString("currency_short_form"));
        }

        if(details.has("per_change")){
            detailsObject.setPerChange(details.getDouble("per_change"));
        }

        if(details.has("trade_volume")){
            detailsObject.setTradeVolume(details.getDouble("trade_volume"));
        }

        return detailsObject;
    }

    /**
     * Helper method to set list of  all entries
     * and returns a Details Object
     * @param list the list to which all the entries to be added
     * @param keys keys for the given list
     * @param prices prices JSON object
     * @param stats stats JSON object**/
    private static void listSetter(List<TickerEntry> list,String[] keys,JSONObject prices, JSONObject stats)throws JSONException{
        for(int i = 0; (i< keys.length)&& (prices != null)&& (stats != null);i++){
            TickerEntry tickerEntry = new TickerEntry();
            // setting the symbol for each entry
            tickerEntry.setSymbol(keys[i]);
            // setting the price for each entry
            if(prices.has(keys[i]))
                tickerEntry.setPrice(prices.getDouble(keys[i]));
            // setting the details for each entry
            if(stats.has(keys[i])){
                JSONObject details = stats.getJSONObject(keys[i]);
                Details detailsObject = getDetails(details);
                tickerEntry.setDetails(detailsObject);
            }
            // Adding each entry to the list
            list.add(tickerEntry);
        }
    }

    //Setter

    public static void setInrList(List<TickerEntry> inr) {
        Ticker.inrList = inr;
    }

    public static void setBitcoinList(List<TickerEntry> bitcoin) {
        Ticker.bitcoinList = bitcoin;
    }

    public static void setEtherList(List<TickerEntry> ether) {
        Ticker.etherList = ether;
    }

    public static void setRippleList(List<TickerEntry> ripple) {
        Ticker.rippleList = ripple;
    }

    //Getter


    public static List<TickerEntry> getInr() {
        return inrList;
    }

    public static List<TickerEntry> getBitcoin() {
        return bitcoinList;
    }

    public static List<TickerEntry> getEther() {
        return etherList;
    }

    public static List<TickerEntry> getRipple() {
        return rippleList;
    }
}
