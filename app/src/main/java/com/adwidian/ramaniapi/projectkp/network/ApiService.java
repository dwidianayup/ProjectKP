package com.adwidian.ramaniapi.projectkp.network;

import com.adwidian.ramaniapi.projectkp.entities.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by apple on 2018/03/04.
 */

public interface ApiService {
    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
