package com.appsaja.wagiman.bookingservices.model;

import com.google.gson.annotations.SerializedName;

public class BookingService {

    @SerializedName("id")
    private String id;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("type_mobil")
    private String typeMobil;

    @SerializedName("jenis_mobil")
    private String jenisMobil;

    @SerializedName("status")
    private String status;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("category")
    private Categories category;

    @SerializedName("customer")
    private Customer customer;

    public BookingService() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
