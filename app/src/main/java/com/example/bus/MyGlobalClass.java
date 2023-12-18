package com.example.bus;

public class MyGlobalClass {
    // Define a static instance of the class
    private static MyGlobalClass instance;

    // Declare variables or methods you want to share
    private String sharedFrom;
    private String sharedTo;

    private String bookDate;

    private String bookTime;

    private String sharedPrice;

    // Private constructor to prevent external instantiation
    private MyGlobalClass() {
        // Initialize variables or perform setup
        sharedFrom = "";
        sharedTo = "";
        bookDate = "";
        bookTime = "";
        sharedPrice="";

    }

    // Method to get the instance of the class
    public static synchronized MyGlobalClass getInstance() {
        if (instance == null) {
            instance = new MyGlobalClass();
        }
        return instance;
    }

    // Getter and setter methods for shared data
    public String getSharedFrom() {
        return sharedFrom;
    }

    public void setSharedFrom(String from) {
        sharedFrom = from;
    }


    public void setBookDate(String Date) {
        bookDate = Date;
    }

    public String getSharedTo() {
        return sharedTo;
    }

    public void setSharedTo(String to) {
        sharedTo = to;
    }

    public String getBookDate(){
        return bookDate;
    }
    public void setBookTime(String time){
        bookTime = time;
    }

    public String getBookTime(){
        return bookTime;
    }

    public void setSharedPrice(String Price){
        sharedPrice=Price;
    }

    public String getSharedPrice(){
        return sharedPrice;
    }
}
