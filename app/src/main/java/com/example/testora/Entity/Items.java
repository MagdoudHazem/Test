package com.example.testora.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")

public class Items {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "mac-address")
    private String macAdress;
    @ColumnInfo(name = "model")
    private String model;
    @ColumnInfo(name = "ip-address")
    private String ipAdress;
    @ColumnInfo(name = "etat")
    private String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Items(String title, String macAdress, String model, String ipAdress) {
        this.title = title;
        this.macAdress = macAdress;
        this.model = model;
        this.ipAdress = ipAdress;
    }

    public Items() {
    }

    @Override
    public String toString() {
        return "Items{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", macAdress='" + macAdress + '\'' +
                ", model='" + model + '\'' +
                ", ipAdress='" + ipAdress + '\'' +
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
