package com.appsaja.wagiman.bookingservices.model;

import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("id")
    private String id;

    @SerializedName("kategori")
    private String kategori;

    public Categories() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
