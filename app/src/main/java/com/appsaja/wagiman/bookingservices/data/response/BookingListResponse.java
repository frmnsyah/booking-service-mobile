package com.appsaja.wagiman.bookingservices.data.response;

import com.appsaja.wagiman.bookingservices.model.Booking;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BookingListResponse extends BaseResponse{

    public List<Booking> getDataBookingArrayList() {
        return dataBookingArrayList;
    }

    public void setDataBookingArrayList(List<Booking> dataBookingArrayList) {
        this.dataBookingArrayList = dataBookingArrayList;
    }

    @SerializedName("data")
    private List<Booking> dataBookingArrayList;
}