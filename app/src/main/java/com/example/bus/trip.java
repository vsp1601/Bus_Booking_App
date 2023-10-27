package com.example.bus;

public class trip {
    String rTime,tTime,pick,drop,price;

    public trip(){}

    public trip(String rTime, String tTime, String pick, String drop, String price) {
        this.rTime = rTime;
        this.tTime = tTime;
        this.pick = pick;
        this.drop = drop;
        this.price = price;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public String gettTime() {
        return tTime;
    }

    public void settTime(String tTime) {
        this.tTime = tTime;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
