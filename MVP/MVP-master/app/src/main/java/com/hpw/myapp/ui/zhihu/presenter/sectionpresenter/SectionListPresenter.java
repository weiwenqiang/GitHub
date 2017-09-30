package com.hpw.myapp.ui.zhihu.presenter.sectionpresenter;

import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

/**
 * Created by hpw on 16/11/5.
 */

public class SectionListPresenter extends ZhihuContract.SectionListPresenter {
    @Override
    public void getSectionListData(int id) {
        mRxManager.add(mModel.getSectionListData(id)
                .subscribe(
                        data -> mView.showContent(data),
                        e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void onStart() {

    }
}
