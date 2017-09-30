package com.chen.mvp.module.register.setPass;

import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.chen.mvp.api.RetrofitService;
import com.chen.mvp.bean.BaseInfo;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by long on 17-4-8.
 */

public class SetPassPresenterImpl implements ISetPassPresenter {

    private ISetPassView mView;

    public SetPassPresenterImpl(ISetPassView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(String user, String pass) {
        mView.showLoading();
        RetrofitService
                .Register(user,pass)
                .subscribe(new Observer<BaseInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseInfo info) {
                        Logger.e(info.toString());
                        mView.loadInfo(info);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getData(String user, String pass, String pass2) {
        if (pass.equals("")) {
           mView.showAnimation("edt_pass");
        } else if (pass2.equals("")) {
            mView.showAnimation("edt_pass2");
        } else if (pass.length() > 0 && pass.length() < 6) {
            mView.showAnimation("pass<6");
        } else if (pass.length() >= 6) {
            mView.showAnimation("pass>=6");
        }
    }
}
