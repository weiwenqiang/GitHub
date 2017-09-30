package com.hpw.myapp.ui.tv.model;

import com.hpw.mvpframe.data.net.RxService;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.api.TvApi;
import com.hpw.myapp.ui.tv.contract.TvContract;

import java.util.List;

import rx.Observable;

/**
 * Created by hpw on 16/12/2.
 */

public class TvMainModel implements TvContract.TvMainModel {

    @Override
    public Observable<List<TabBean>> getTabList() {
        return RxService.createApi(TvApi.class)
                .getTabList()
                .compose(RxUtil.rxSchedulerHelper());
    }
}
