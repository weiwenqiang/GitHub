package com.chen.mvp.module.register.inputCode;

import com.chen.mvp.module.base.IBasePresenter;

/**
 * Created by long on 17-4-7.
 */

public interface IInputCodePresenter extends IBasePresenter{

    /**
     * 获取验证码
     */
    void getData(String phone);

    /**
     * 检验验证码
     * @param code
     */
    void checkCode(String code);
}
