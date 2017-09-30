package com.chen.test;

import android.app.Application;

import com.chen.test.common.RetrofitService;
import com.orhanobut.logger.Logger;

/**
 * Created by long on 17-3-24.
 */

public class MyApplication extends Application {

    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = (MyApplication) getApplicationContext();
        Logger.init("LogTAG");
        RetrofitService.init();
    }

}
