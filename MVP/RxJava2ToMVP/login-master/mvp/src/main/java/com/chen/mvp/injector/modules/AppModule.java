package com.chen.mvp.injector.modules;

import android.content.Context;

import com.chen.mvp.App.App;
import com.chen.mvp.bean.UserInfo;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/19.
 * Application Module
 */
@Module
public class AppModule {

    private final App mApplication;



    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }



}
