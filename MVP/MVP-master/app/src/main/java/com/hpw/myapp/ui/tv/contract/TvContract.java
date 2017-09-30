package com.hpw.myapp.ui.tv.contract;

import com.hpw.mvpframe.base.CoreBaseModel;
import com.hpw.mvpframe.base.CoreBasePresenter;
import com.hpw.mvpframe.base.CoreBaseView;
import com.hpw.myapp.ui.tv.model.FirstBannerBean;
import com.hpw.myapp.ui.tv.model.FirstBean;
import com.hpw.myapp.ui.tv.model.OtherBean;
import com.hpw.myapp.ui.tv.model.TabBean;
import com.hpw.myapp.ui.tv.model.TvShowBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hpw on 16/12/2.
 */

public interface TvContract {
    //主页接口
    abstract class TvMainPresenter extends CoreBasePresenter<TvContract.TvMainModel, TvContract.TvMainView> {
        public abstract void getTabList();
    }

    interface TvMainModel extends CoreBaseModel {
        Observable<List<TabBean>> getTabList();
    }

    interface TvMainView extends CoreBaseView {
        void showTabList(List<TabBean> mTabs);
    }

    //Other接口
    abstract class OtherPresenter extends CoreBasePresenter<TvContract.OtherModel, TvContract.OtherView> {
        public abstract void getOtherData(String url);
    }

    interface OtherModel extends CoreBaseModel {
        Observable<OtherBean> getOtherData(String url);
    }

    interface OtherView extends CoreBaseView {
        void showContent(OtherBean info);
    }

    //First接口
    abstract class FirstPresenter extends CoreBasePresenter<TvContract.FirstModel, TvContract.FirstView> {
        public abstract void getFirstData();

        public abstract void getBannerData();

        public abstract void startInterval();
    }

    interface FirstModel extends CoreBaseModel {
        Observable<FirstBean> getFirstData();

        Observable<Object> getBannerData();
    }

    interface FirstView extends CoreBaseView {
        void showContent(FirstBean info);

        void showBannerContent(List<FirstBannerBean> info);

        void doInterval(int i);
    }

    //TvShow接口
    abstract class TvShowPresenter extends CoreBasePresenter<TvContract.TvShowModel, TvContract.TvShowView> {
        public abstract void onTvShow(String url);
    }

    interface TvShowModel extends CoreBaseModel {
        Observable<TvShowBean> onTvShow(String url);
    }

    interface TvShowView extends CoreBaseView {
        void showContent(TvShowBean info);

        void onConnecting();

        void onReConnecting();

        void onConnectSucces();

        void onConnectFailed();

        void onPlayComleted();

        void onPlayerStart();

        void onPlayePause();
    }
}
