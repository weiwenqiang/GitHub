package com.chen.mvp.injector.components;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.injector.modules.SetPassModule;
import com.chen.mvp.module.register.setPass.SetPassActivity;

import dagger.Component;

/**
 * Created by long on 17-4-8.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = SetPassModule.class)
public interface SetPassComponent {

    void inject(SetPassActivity activity);
}
