package com.bitwisey.retrofitcrudapp.Networking;
import com.bitwisey.retrofitcrudapp.Model.RetroInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("insertdata.php")
    Call<ResponseBody>insertdata(
            @Field("name")String name,
            @Field("number")String number

    );
    @GET("getdata.php")
    Call<List<RetroInfo>> getAllInfo();

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseBody> deleteinfo( @Field("id") int id );



    @FormUrlEncoded
    @POST("updateData.php")
    Call<ResponseBody> updateinfo( @Field("id") int id,@Field("name") String name ,@Field("number") String number  );
}

