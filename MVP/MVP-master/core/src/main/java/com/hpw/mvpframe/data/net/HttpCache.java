package com.hpw.mvpframe.data.net;

import com.hpw.mvpframe.CoreApp;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by hpw on 16/11/2.
 */
public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(CoreApp.getAppContext().getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
