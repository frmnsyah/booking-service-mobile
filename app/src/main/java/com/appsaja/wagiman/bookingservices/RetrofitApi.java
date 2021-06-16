package com.appsaja.wagiman.bookingservices;

import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;
import com.appsaja.wagiman.bookingservices.data.response.BookingListResponse;
import com.appsaja.wagiman.bookingservices.data.response.DataLookupResponse;
import com.appsaja.wagiman.bookingservices.data.response.LoginResponse;
import com.appsaja.wagiman.bookingservices.data.response.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApi {
    @FormUrlEncoded
    @POST("authenticate/login")
    Call<LoginResponse> postLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("authenticate/register")
    Call<BaseResponse> postRegistration(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("booking")
    Call<BaseResponse> postBooking(
            @Field("cust_id") String custId,
            @Field("tanggal") String tanggal,
            @Field("jam") String jam,
            @Field("cat_id") String catId,
            @Field("type_mobil") String type,
            @Field("jenis_mobil") String jenis,
            @Field("lokasi") String lokasi,
            @Field("tahun") String tahun
    );

    @GET("type-mobil")
    Call<DataLookupResponse> getTypeMobil();

    @GET("jenis-mobil")
    Call<DataLookupResponse> getJenisMobil();

    @GET("lokasi")
    Call<DataLookupResponse> getLokasi();

    @GET("kategori")
    Call<DataLookupResponse> getKategori();

    @FormUrlEncoded
    @POST("booking-list")
    Call<BookingListResponse> getBookingList(@Field("cust_id") String custId);

    @GET("service/{service_id}")
    Call<ServiceResponse> getServiceDetail(@Path("service_id") String id);

    @FormUrlEncoded
    @POST("update-account")
    Call<BaseResponse> postUpdateAccount(
            @Field("user_id") int userId,
            @Field("nama") String nama,
            @Field("no_hp") String noHp,
            @Field("alamat") String alamat
    );
}
