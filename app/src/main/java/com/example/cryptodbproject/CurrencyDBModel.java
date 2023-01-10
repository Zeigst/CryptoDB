package com.example.cryptodbproject;

public class CurrencyDBModel {
    private String name;
    private String symbol;
    private double price;
    private double change1h;
    private double change24h;
    private double market_cap;
    private double c_supply;
    private String last_updated;

    public CurrencyDBModel(String name, String symbol, double price, double change1h, double change24h, double market_cap, double c_supply, String last_updated) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change1h = change1h;
        this.change24h = change24h;
        this.market_cap = market_cap;
        this.c_supply = c_supply;
        this.last_updated = last_updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange1h() {
        return change1h;
    }

    public void setChange1h(double change1h) {
        this.change1h = change1h;
    }

    public double getChange24h() {
        return change24h;
    }

    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public double getC_supply() {
        return c_supply;
    }

    public void setC_supply(double c_supply) {
        this.c_supply = c_supply;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

}
