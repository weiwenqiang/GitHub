package com.chen.mvp.injector.components;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.injector.modules.InputCodeModule;
import com.chen.mvp.module.register.inputCode.InputCodeActivity;

import dagger.Component;

/**
 * Created by long on 17-4-7.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = InputCodeModule.class)
public interface InputCodeComponent {

    void inject(InputCodeActivity activity);
}
