package com.example.jilson.cryptoapp;

final class TickerEntry {

    private String symbol;
    private Double price;
    private Details details;

    // Setter

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    //Getter

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public Details getDetails() {
        return details;
    }
}

class Details{
    private Double highestBid;
    private Double lowestAsk;
    private Double lastTradedPrice;
    private Double min24Hrs;
    private Double max24Hrs;
    private Double vol24Hrs;
    private String currencyFullForm;
    private String currencyShortForm;
    private Double perChange;
    private Double tradeVolume;

    //Setters


    void setHighestBid(Double highestBid) {
        this.highestBid = highestBid;
    }

    void setLowestAsk(Double lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    void setLastTradedPrice(Double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    void setMin24Hrs(Double min24Hrs) {
        this.min24Hrs = min24Hrs;
    }

    void setMax24Hrs(Double max24Hrs) {
        this.max24Hrs = max24Hrs;
    }

    void setVol24Hrs(Double vol24Hrs) {
        this.vol24Hrs = vol24Hrs;
    }

    void setCurrencyFullForm(String currencyFullForm) {
        this.currencyFullForm = currencyFullForm;
    }

    void setCurrencyShortForm(String currencyShortForm) {
        this.currencyShortForm = currencyShortForm;
    }

    void setPerChange(Double perChange) {
        this.perChange = perChange;
    }

    void setTradeVolume(Double tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    //Getters

    public Double getHighestBid() {
        return highestBid;
    }

    public Double getLowestAsk() {
        return lowestAsk;
    }

    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public Double getMin24Hrs() {
        return min24Hrs;
    }

    public Double getMax24Hrs() {
        return max24Hrs;
    }

    public Double getVol24Hrs() {
        return vol24Hrs;
    }

    public String getCurrencyFullForm() {
        return currencyFullForm;
    }

    public String getCurrencyShortForm() {
        return currencyShortForm;
    }

    public Double getPerChange() {
        return perChange;
    }

    public Double getTradeVolume() {
        return tradeVolume;
    }
}
