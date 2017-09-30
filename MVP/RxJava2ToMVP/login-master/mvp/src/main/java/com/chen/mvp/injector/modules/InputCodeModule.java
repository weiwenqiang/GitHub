package com.chen.mvp.injector.modules;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.module.register.inputCode.IInputCodeView;
import com.chen.mvp.module.register.inputCode.InputCodePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 17-4-7.
 */
@Module
public class InputCodeModule {

    private IInputCodeView mView;

    public InputCodeModule(IInputCodeView mView){
        this.mView  = mView;
    }

    @PerActivity
    @Provides
    public InputCodePresenterImpl providePresenter(){
        return new InputCodePresenterImpl(mView);
    }
}
