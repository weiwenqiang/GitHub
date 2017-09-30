package com.chen.mvp.injector.modules;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.module.login.ILoginView;
import com.chen.mvp.module.login.LoginPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 17-4-5.
 */
@Module
public class LoginModule {
    private ILoginView mView;

    public LoginModule(ILoginView mView){
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public LoginPresenterImpl providePresenter(){
        return new LoginPresenterImpl(mView);
    }
}
