package com.example.myapplication.model;

public class CryptoCoin implements Comparable<CryptoCoin>{
    private String name;
    private float price;
    private float change;
    private float cap;
    private float supply;

    public CryptoCoin(String name, float price, float change, float cap, float supply) {
        this.name = name;
        this.price = price;
        this.change = change;
        this.cap = cap;
        this.supply = supply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public float getCap() {
        return cap;
    }

    public void setCap(float cap) {
        this.cap = cap;
    }

    public float getSupply() {
        return supply;
    }

    public void setSupply(float supply) {
        this.supply = supply;
    }

    @Override
    public int compareTo(CryptoCoin cryptoCoin) {
        return 0;
    }
}
