package com.appsaja.wagiman.bookingservices;

import com.appsaja.wagiman.bookingservices.data.response.BaseResponse;
import com.appsaja.wagiman.bookingservices.data.response.BookingListResponse;
import com.appsaja.wagiman.bookingservices.data.response.DataLookupResponse;
import com.appsaja.wagiman.bookingservices.data.response.LoginResponse;
import com.appsaja.wagiman.bookingservices.data.response.ServiceResponse;
import com.appsaja.wagiman.bookingservices.model.MovieDetail;
import com.appsaja.wagiman.bookingservices.model.MovieResponse;
import com.appsaja.wagiman.bookingservices.model.ReviewResponse;
import com.appsaja.wagiman.bookingservices.model.Service;
import com.appsaja.wagiman.bookingservices.model.TvDetail;
import com.appsaja.wagiman.bookingservices.model.TvResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopMovie(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReview(@Path("movie_id") String id, @Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TvResponse> getPopularTv(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/{tv_id}")
    Call<TvDetail> getDetailTv(@Path("tv_id") String tvid, @Query("api_key") String apiKey);

    @GET("authenticate")
    Call<BaseResponse> testApi();

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
