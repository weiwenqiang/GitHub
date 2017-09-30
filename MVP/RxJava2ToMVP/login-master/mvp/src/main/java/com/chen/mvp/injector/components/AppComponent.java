package com.chen.mvp.injector.components;

import android.content.Context;

import com.chen.mvp.bean.UserInfo;
import com.chen.mvp.injector.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by long on 17-4-5.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

}
