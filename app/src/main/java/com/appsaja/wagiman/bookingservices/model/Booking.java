package com.appsaja.wagiman.bookingservices.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Booking {

    @SerializedName("id")
    private String id;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("type_mobil")
    private String typeMobil;

    @SerializedName("jenis_mobil")
    private String jenisMobil;

    @SerializedName("status")
    private String status;

    @SerializedName("tanggal")
    private String tanggal;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Booking() {
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

    public String getTypeMobil() {
        return typeMobil;
    }

    public void setTypeMobil(String typeMobil) {
        this.typeMobil = typeMobil;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public void setJenisMobil(String jenisMobil) {
        this.jenisMobil = jenisMobil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
