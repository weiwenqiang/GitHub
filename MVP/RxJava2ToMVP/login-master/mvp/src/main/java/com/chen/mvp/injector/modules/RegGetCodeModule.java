package com.chen.mvp.injector.modules;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.module.register.getCode.IRegGetCodeView;
import com.chen.mvp.module.register.getCode.RegGetCodePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 17-4-7.
 */
@Module
public class RegGetCodeModule {

    private IRegGetCodeView mView;

    public RegGetCodeModule(IRegGetCodeView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public RegGetCodePresenterImpl providePresenter(){
        return new RegGetCodePresenterImpl(mView);
    }
}
