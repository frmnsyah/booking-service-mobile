package com.appsaja.wagiman.bookingservices.data.response;

import com.appsaja.wagiman.bookingservices.model.Service;
import com.google.gson.annotations.SerializedName;


public class ServiceResponse extends BaseResponse{

    @SerializedName("data")
    private Service data;

    public Service getData() {
        return data;
    }

    public void setData(Service data) {
        this.data = data;
    }
}