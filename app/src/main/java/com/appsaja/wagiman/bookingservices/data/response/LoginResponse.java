package com.appsaja.wagiman.bookingservices.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponse{
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    @SerializedName("customer_id")
    @Expose
    private int customerId;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("no_hp")
    @Expose
    private String noHp;


}