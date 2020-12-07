package com.example.testora.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class myitems {
    @SerializedName("id")
    @Expose
    private int uid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("macadress")
    @Expose    private String macAdress;
    @SerializedName("model")
    @Expose    private String model;
    @SerializedName("ipadress")
    @Expose    private String ipAdress;
    @SerializedName("etat")
    @Expose    private String etat;
    @SerializedName("date")
    @Expose
    private String date;
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public myitems(String title, String macAdress, String model, String ipAdress,String etat,String date) {
        this.title = title;
        this.macAdress = macAdress;
        this.model = model;
        this.ipAdress = ipAdress;
        this.etat=etat;
        this.date=date;
    }

    public myitems() {
    }

    public myitems(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Items{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", macAdress='" + macAdress + '\'' +
                ", model='" + model + '\'' +
                ", ipAdress='" + ipAdress + '\'' +
                ", etat='" + etat + '\'' +
                ", date=" + date +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
