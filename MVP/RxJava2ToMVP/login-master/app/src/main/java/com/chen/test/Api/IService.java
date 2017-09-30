package com.chen.test.Api;

import com.chen.test.Entity.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.chen.test.common.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by long on 17-3-24.
 */

public interface IService {
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("Register")
    Observable<BaseEntity> register(@Query("user") String user, @Query("pass") String pass);

    @Headers(CACHE_CONTROL_NETWORK)
    @POST("login")
    Observable<BaseEntity> login(@Query("user") String user, @Query("pass") String pass);

    @Headers(CACHE_CONTROL_NETWORK)
    @POST("GetCheckCode")
    Observable<BaseEntity> getCheckCode(@Query("phone") String phone);
}
