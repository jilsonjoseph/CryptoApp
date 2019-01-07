package com.example.jilson.cryptoapp;

final class TickerEntry {

    // Each entry stores 3 member field
    private String symbol;
    private Double price;
    private Details details;

    // Setter

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    void setPrice(Double price) {
        this.price = price;
    }

    void setDetails(Details details) {
        this.details = details;
    }

    //Getter

    public String getSymbol() {
        return symbol;
    }

    Double getPrice() {
        return price;
    }

    Details getDetails() {
        return details;
    }
}

class Details{
    // a details object has all details
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

    void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    void setPerChange(Double perChange) {
        this.perChange = perChange;
    }

    void setTradeVolume(Double tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    //Getters

    Double getHighestBid() {
        return highestBid;
    }

    Double getLowestAsk() {
        return lowestAsk;
    }

    Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    Double getMin24Hrs() {
        return min24Hrs;
    }

    Double getMax24Hrs() {
        return max24Hrs;
    }

    Double getVol24Hrs() {
        return vol24Hrs;
    }

    String getCurrencyFullForm() {
        return currencyFullForm;
    }

    String getCurrencyShortForm() {
        return currencyShortForm;
    }

    String getBaseCurrency() {
        return baseCurrency;
    }

    Double getPerChange() {
        return perChange;
    }

    Double getTradeVolume() {
        return tradeVolume;
    }
}
