package com.appsaja.wagiman.bookingservices.network;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
//    @GET("restapi/messages.json")
//    Call<List<Message>> getInbox();
//
//    @GET("restapi/categories")
//    Call<List<Categories>> getCategories();
//
//    @GET("restapi/categories")
//    Call<ResponseBody> getCategoriesForDropdown();
//
//    @GET("restapi/placements")
//    Call<List<Placements>> getPlacements();
//
//    @GET("restapi/placements")
//    Call<ResponseBody> getPlacementsForDropdown();
//
//    @FormUrlEncoded
//    @POST("restapi/categories/add")
//    Call<Categories> insertCategory(@Field("category_name") String categoryName);
//
//    @FormUrlEncoded
//    @POST("restapi/categories/edit")
//    Call<ResponseBody> updateCategory(@Field("category_id") String categoryId, @Field("category_name") String categoryName);
//
//    @FormUrlEncoded
//    @POST("restapi/categories/delete")
//    Call<ResponseBody> removeCategory(@Field("category_id") String categoryId);
//
//    @FormUrlEncoded
//    @POST("restapi/placements/add")
//    Call<Placements> insertPlacement(@Field("room") String room);
//
//    @FormUrlEncoded
//    @POST("restapi/placements/edit")
//    Call<ResponseBody> updatePlacement(@Field("placement_id") String placementId, @Field("room") String room);
//
//    @FormUrlEncoded
//    @POST("restapi/placements/delete")
//    Call<ResponseBody> removePlacement(@Field("placement_id") String placementId);
//
//    @FormUrlEncoded
//    @POST("restapi/assets/find")
//    Call<ResponseBody> assetFind(@Field("asset_code") String assetCode);
//
//    @GET("restapi/inventories")
//    Call<List<Assets>> getInventories();
//
//    @FormUrlEncoded
//    @POST("restapi/inventories/detail")
//    Call<ResponseBody> detailFind(@Field("asset_code") String assetCode);
//
//    @GET("restapi/depreciations")
//    Call<List<AssetDepreciation>> getDepreciations();
//
//    @FormUrlEncoded
//    @POST("restapi/depreciation/detail")
//    Call<List<AssetDepreciationDetail>> getDepreciationDetail(@Field("asset_code") String assetCode);
//
//    @GET("restapi/lookup/inventories/status")
//    Call<ResponseBody> getLookupInventoriesStatus();
//
//    @Multipart
//    @POST("restapi/inventories/add")
//    Call<ResponseBody> inventoriesAdd(@Part MultipartBody.Part assetPicture);
}
