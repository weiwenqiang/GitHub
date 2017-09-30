package com.chen.mvp.injector.components;

import android.app.Activity;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
