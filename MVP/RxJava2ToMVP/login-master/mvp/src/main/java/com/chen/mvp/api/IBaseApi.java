package com.chen.mvp.api;

import com.chen.mvp.bean.BaseInfo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.chen.mvp.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by long on 17-4-5.
 * API接口
 */

public interface IBaseApi {

    /**
     * 用户注册
     * eg:http://localhost:8080/web/Register
     * @param user 用户名
     * @param pass 密码
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("Register")
    Observable<BaseInfo> register(@Query("user") String user, @Query("pass") String pass);

    /**
     * 用户登录
     * eg:http://localhost:8080/web/Login
     * @param user 用户名
     * @param pass 密码
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("Login")
    Observable<BaseInfo> login(@Query("user") String user, @Query("pass") String pass);

    /**
     * 获取手机短信验证码
     * eg:http://localhost:8080/web/GetCheckCode
     * @param phone 手机号
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("GetCheckCode")
    Observable<BaseInfo> getCheckCode(@Query("phone") String phone);

    /**
     * 验证用户输入的验证码是否正确
     * eg:http://localhost:8080/web/VerifyCheckCode
     * @param checkCode
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("VerifyCheckCode")
    Observable<BaseInfo> verifyCheckCode(@Query("code") String checkCode);

}
