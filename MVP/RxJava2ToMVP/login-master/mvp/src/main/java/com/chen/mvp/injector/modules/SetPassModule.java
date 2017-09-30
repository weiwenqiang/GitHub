package com.chen.mvp.injector.modules;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.module.register.setPass.ISetPassView;
import com.chen.mvp.module.register.setPass.SetPassPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 17-4-8.
 */
@Module
public class SetPassModule {

    private ISetPassView mView;

    public SetPassModule(ISetPassView mView){
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public SetPassPresenterImpl providePresenter(){
        return new SetPassPresenterImpl(mView);
    }

}
