package com.appsaja.wagiman.bookingservices.model;

import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("id")
    private String id;

    @SerializedName("booking_id")
    private String userId;

    @SerializedName("tanggal")
    private String nama;

    @SerializedName("mulai")
    private String alamat;

    @SerializedName("mekanik")
    private String noHp;

    @SerializedName("no_polisi")
    private String noPolisi;

    @SerializedName("catatan")
    private String catatan;

    @SerializedName("total_biaya")
    private String totalBiaya;

    @SerializedName("booking")
    private BookingService booking;

    public Service() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(String totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public BookingService getBooking() {
        return booking;
    }

    public void setBooking(BookingService booking) {
        this.booking = booking;
    }
}
