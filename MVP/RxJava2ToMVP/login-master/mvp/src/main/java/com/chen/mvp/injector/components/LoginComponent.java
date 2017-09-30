package com.chen.mvp.injector.components;

import com.chen.mvp.injector.PerActivity;
import com.chen.mvp.injector.modules.LoginModule;
import com.chen.mvp.module.login.LoginActivity;

import dagger.Component;

/**
 * Created by long on 17-4-5.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
