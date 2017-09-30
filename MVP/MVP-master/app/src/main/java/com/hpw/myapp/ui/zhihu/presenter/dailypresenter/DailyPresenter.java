package com.hpw.myapp.ui.zhihu.presenter.dailypresenter;

import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by hpw on 16/11/2.
 */
public class DailyPresenter extends ZhihuContract.DailyPresenter {
    private int topCount = 0;
    private int currentTopCount = 0;

    @Override
    public void onStart() {

    }

    @Override
    public void getDailyData() {
        mRxManager.add(mModel
                .getDailyData()
                .subscribe(
                        dailyListBean -> {
                            mView.showContent(dailyListBean);
                            topCount = dailyListBean.getTop_stories().size();
                        }, e -> mView.showError("数据加载失败ヽ(≧Д≦)ノ")
                ));
    }

    @Override
    public void startInterval() {
        mRxManager.add(Observable.interval(5, TimeUnit.SECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                            if (currentTopCount == topCount)
                                currentTopCount = 0;
                            mView.doInterval(currentTopCount++);
                        }
                ));
    }
}
