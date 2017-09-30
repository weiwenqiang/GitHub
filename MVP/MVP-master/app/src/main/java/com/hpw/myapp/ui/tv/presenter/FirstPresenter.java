package com.hpw.myapp.ui.tv.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.ui.tv.contract.TvContract;
import com.hpw.myapp.ui.tv.model.FirstBannerBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by hpw on 16/12/4.
 */

public class FirstPresenter extends TvContract.FirstPresenter {
    private int topCount = 0;
    private int currentTopCount = 0;

    @Override
    public void getFirstData() {
        mRxManager.add(mModel.getFirstData()
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void getBannerData() {
        mRxManager.add(mModel.getBannerData()
                .subscribe(
                        data -> {
                            List<FirstBannerBean> banners = null;
                            JSONArray jsonArray = new JSONObject((Map) data).optJSONArray("app-focus");
                            Gson mGson = new Gson();
                            Type type = new TypeToken<List<FirstBannerBean>>() {
                            }.getType();
                            if (jsonArray != null) {
                                banners = mGson.fromJson(jsonArray.toString(), type);
                            }
                            mView.showBannerContent(banners);
                            topCount = banners.size();
                        }, e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void startInterval() {
        mRxManager.add(Observable.interval(2, TimeUnit.SECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                            if (currentTopCount == topCount)
                                currentTopCount = 0;
                            mView.doInterval(currentTopCount++);
                        }
                ));
    }

    @Override
    public void onStart() {

    }
}
