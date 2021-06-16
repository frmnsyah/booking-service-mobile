package com.appsaja.wagiman.bookingservices.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataLookupResponse extends BaseResponse{

    public ArrayList<DataLookup> getSpinnerModalClassArrayList() {
        return spinnerModalClassArrayList;
    }

    public void setSpinnerModalClassArrayList(ArrayList<DataLookup> couponsListingModalArrayList) {
        this.spinnerModalClassArrayList = couponsListingModalArrayList;
    }

    @SerializedName("data")
    private ArrayList<DataLookup> spinnerModalClassArrayList;

    public class DataLookup {

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }

        @SerializedName("id")
        private String id;

        @SerializedName("values")
        private String values;
    }
}