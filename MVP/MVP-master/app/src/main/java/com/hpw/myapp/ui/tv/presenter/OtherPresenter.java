package com.hpw.myapp.ui.tv.presenter;

import com.hpw.myapp.ui.tv.contract.TvContract;

/**
 * Created by hpw on 16/12/3.
 */

public class OtherPresenter extends TvContract.OtherPresenter {
    @Override
    public void onStart() {
    }

    @Override
    public void getOtherData(String url) {
        mRxManager.add(mModel.getOtherData(url)
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }
}
