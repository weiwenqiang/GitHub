package com.hpw.myapp.api;

import com.hpw.mvpframe.data.entity.CoreDataResponse;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WXItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hpw on 16/11/6.
 */

public interface WechatApi {
    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<CoreDataResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<CoreDataResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
