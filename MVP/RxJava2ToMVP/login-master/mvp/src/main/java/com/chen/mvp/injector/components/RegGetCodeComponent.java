package com.chen.mvp.injector.components;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.injector.modules.RegGetCodeModule;
import com.chen.mvp.module.register.getCode.RegGetCodeActivity;

import dagger.Component;

/**
 * Created by long on 17-4-7.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = RegGetCodeModule.class)
public interface RegGetCodeComponent {

    void inject(RegGetCodeActivity activity);
}
