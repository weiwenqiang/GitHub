package com.chen.mvp.module.register.getCode;

import com.chen.mvp.module.base.IBasePresenter;

/**
 * Created by long on 17-4-7.
 */

public interface IRegGetCodePresenter extends IBasePresenter{

    /**
     * 获取数据
     */
    void getData(String phone);
}
