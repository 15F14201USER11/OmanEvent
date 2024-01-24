package com.example.omanevent.models;

import com.google.firebase.Timestamp;
import com.google.type.DateTime;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class Event implements Serializable {
    private String ID;
    private String Ctegory;
    private String Name;
    private Date From;
    private Date To;
    private String PlaceAddress;
    private String Description;
    private double price;
    private String PlaceOwnerID;


    public Event(String id, String category, String name, Date from, Date to, String placeAddress, String description, double price, boolean isForFamily, String placeOwnerID) throws ParseException {


        ID = id;
        Ctegory = category;
        Name = name;
        From = from;//new Date(from)
        To = to;
        PlaceAddress = placeAddress;
        Description = description;
        this.price = price;
        PlaceOwnerID = placeOwnerID;
    }

    public Event() {

    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCtegory(String ctegory) {
        Ctegory = ctegory;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFrom(Date from) {
        From = from;
    }

    public void setTo(Date to) {
        To = to;
    }

    public void setPlaceAddress(String placeAddress) {
        PlaceAddress = placeAddress;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public String getCtegory() {
        return Ctegory;
    }

    public String getName() {
        return Name;
    }

    public Date getFrom() {
        return From;
    }

    public Date getTo() {
        return To;
    }

    public String getPlaceAddress() {
        return PlaceAddress;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return price;
    }
    public String getPlaceOwnerID() {
        return PlaceOwnerID;
    }

    public void setPlaceOwnerID(String placeOwnerID) {
        PlaceOwnerID = placeOwnerID;
    }
}
