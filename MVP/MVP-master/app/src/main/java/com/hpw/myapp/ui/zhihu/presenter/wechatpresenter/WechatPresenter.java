package com.hpw.myapp.ui.zhihu.presenter.wechatpresenter;

import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

/**
 * Created by hpw on 16/11/6.
 */
public class WechatPresenter extends ZhihuContract.WechatPresenter {
    @Override
    public void onStart() {
        getWechatData(50, 1);
    }

    @Override
    public void getWechatData(int num, int page) {
        mRxManager.add(mModel.getWechatData(num, page)
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError(e.toString())
                ));
    }
}
