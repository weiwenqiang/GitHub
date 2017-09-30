package com.hpw.myapp.ui.zhihu.model.sectionmodel;

import com.hpw.mvpframe.data.net.RxService;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.api.ZhiHuApi;
import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

import rx.Observable;

/**
 * Created by hpw on 16/11/5.
 */

public class SectionModel implements ZhihuContract.SectionModel {
    @Override
    public Observable<SectionListBean> getSectionData() {
        return RxService.createApi(ZhiHuApi.class).getSectionList().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<SectionChildListBean> getSectionListData(int id) {
        return RxService.createApi(ZhiHuApi.class).getSectionChildList(id).compose(RxUtil.rxSchedulerHelper());
    }
}
