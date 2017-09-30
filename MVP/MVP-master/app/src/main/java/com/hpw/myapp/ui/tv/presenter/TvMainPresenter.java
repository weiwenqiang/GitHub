package com.hpw.myapp.ui.tv.presenter;

import com.hpw.myapp.ui.tv.contract.TvContract;

/**
 * Created by hpw on 16/12/2.
 */

public class TvMainPresenter extends TvContract.TvMainPresenter {
    @Override
    public void getTabList() {
        mRxManager.add(mModel.getTabList()
                .subscribe(
                        data -> mView.showTabList(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void onStart() {
        getTabList();
    }
}
