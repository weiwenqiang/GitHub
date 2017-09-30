package com.chen.mvp.module.login;

import com.chen.mvp.api.RetrofitService;
import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.module.base.IBasePresenter;
import com.chen.mvp.utils.MobilePhone;
import com.orhanobut.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by long on 17-4-5.
 */

public class LoginPresenterImpl implements IBasePresenter {

    private ILoginView mView;

    public LoginPresenterImpl(ILoginView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(String user,String pass) {
        if (!MobilePhone.isMobileNO(user)) {
            mView.showToast("请输入正确的手机号!");
            mView.showAnimation("");
            return;
        }
        mView.showLoading();
        RetrofitService
                .login(user,pass)
                .subscribe(new Observer<BaseInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseInfo baseInfo) {
                        Logger.e(baseInfo.toString());
                        mView.loadInfo(baseInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
