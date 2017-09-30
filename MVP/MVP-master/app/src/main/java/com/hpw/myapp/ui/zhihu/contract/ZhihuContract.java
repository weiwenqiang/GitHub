package com.hpw.myapp.ui.zhihu.contract;

import com.hpw.mvpframe.base.CoreBaseModel;
import com.hpw.mvpframe.base.CoreBasePresenter;
import com.hpw.mvpframe.base.CoreBaseView;
import com.hpw.myapp.ui.zhihu.model.dailymodel.DailyListBean;
import com.hpw.myapp.ui.zhihu.model.dailymodel.ZhihuDetailBean;
import com.hpw.myapp.ui.zhihu.model.sectionmodel.SectionChildListBean;
import com.hpw.myapp.ui.zhihu.model.sectionmodel.SectionListBean;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WXItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hpw on 16/10/31.
 */

public interface ZhihuContract {
    //主页接口
    abstract class ZhihuMainPresenter extends CoreBasePresenter<ZhihuMainModel, ZhihuMainView> {
        public abstract void getTabList();
    }

    interface ZhihuMainModel extends CoreBaseModel {
        String[] getTabs();
    }

    interface ZhihuMainView extends CoreBaseView {
        void showTabList(String[] mTabs);
    }

    //daily所有接口(model写在了一起,view presenter分开写)
    abstract class DailyPresenter extends CoreBasePresenter<DailyModel, DailyView> {
        public abstract void getDailyData();

        public abstract void startInterval();
    }

    interface DailyModel extends CoreBaseModel {
        Observable<DailyListBean> getDailyData();

        Observable<ZhihuDetailBean> getZhihuDetails(int anInt);
    }

    interface DailyView extends CoreBaseView {
        void showContent(DailyListBean info);

        void doInterval(int i);
    }

    abstract class ZhihuDetailsPresenter extends CoreBasePresenter<DailyModel, ZhihuDetailsView> {
        public abstract void getZhihuDetails(int anInt);
    }

    interface ZhihuDetailsView extends CoreBaseView {
        void showContent(ZhihuDetailBean info);
    }

    //section所有接口
    abstract class SectionPresenter extends CoreBasePresenter<SectionModel, SectionView> {

        public abstract void getSectionData();
    }

    interface SectionModel extends CoreBaseModel {

        Observable<SectionListBean> getSectionData();

        Observable<SectionChildListBean> getSectionListData(int id);
    }

    interface SectionView extends CoreBaseView {
        void showContent(SectionListBean info);

    }

    abstract class SectionListPresenter extends CoreBasePresenter<SectionModel, SectionListView> {

        public abstract void getSectionListData(int id);
    }

    interface SectionListView extends CoreBaseView {
        void showContent(SectionChildListBean info);
    }

    abstract class WechatPresenter extends CoreBasePresenter<WechatModel, WechatView> {
        public abstract void getWechatData(int num, int page);
    }

    interface WechatModel extends CoreBaseModel {
        Observable<List<WXItemBean>> getWechatData(int num, int page);
    }

    interface WechatView extends CoreBaseView {
        void showContent(List<WXItemBean> mList);
    }
}

