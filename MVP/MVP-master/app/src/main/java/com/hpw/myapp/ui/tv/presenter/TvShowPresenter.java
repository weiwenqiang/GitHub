package com.hpw.myapp.ui.tv.presenter;

import com.hpw.myapp.ui.tv.contract.TvContract;

/**
 * Created by hpw on 16/12/3.
 */

public class TvShowPresenter extends TvContract.TvShowPresenter{
    @Override
    public void onTvShow(String url) {
        mRxManager.add(mModel.onTvShow(url)
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void onStart() {

    }
}
