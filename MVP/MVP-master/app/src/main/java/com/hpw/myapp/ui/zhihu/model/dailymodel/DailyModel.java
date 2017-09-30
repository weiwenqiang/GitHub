package com.hpw.myapp.ui.zhihu.model.dailymodel;

import com.hpw.mvpframe.data.net.RxService;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.api.ZhiHuApi;
import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

import rx.Observable;

/**
 * Created by hpw on 16/11/2.
 */
public class DailyModel implements ZhihuContract.DailyModel {

    @Override
    public Observable<DailyListBean> getDailyData() {
        return RxService.createApi(ZhiHuApi.class).getDailyList().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDetailBean> getZhihuDetails(int anInt) {
        return RxService.createApi(ZhiHuApi.class).getDetailInfo(anInt).compose(RxUtil.rxSchedulerHelper());
    }
}
