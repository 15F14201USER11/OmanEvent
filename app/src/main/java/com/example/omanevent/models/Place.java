package com.example.omanevent.models;

public class Place {
    private String Address;
    private String Name;

    public void setAddress(String address) {
        Address = address;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUser_owenr_ID(String user_owenr_ID) {
        this.user_owenr_ID = user_owenr_ID;
    }

    public Place(String address, String name, String user_owenr_ID) {
        Address = address;
        Name = name;
        this.user_owenr_ID = user_owenr_ID;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public String getUser_owenr_ID() {
        return user_owenr_ID;
    }

    private String user_owenr_ID;
}
