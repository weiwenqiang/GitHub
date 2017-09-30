package com.chen.mvp.module.register.setPass;

import android.widget.TextView;

import com.chen.mvp.module.base.IBasePresenter;
import com.chen.mvp.utils.ClearEditText;

/**
 * Created by long on 17-4-8.
 */

public interface ISetPassPresenter extends IBasePresenter {

    void getData(String user, String pass,String pass2);

}
