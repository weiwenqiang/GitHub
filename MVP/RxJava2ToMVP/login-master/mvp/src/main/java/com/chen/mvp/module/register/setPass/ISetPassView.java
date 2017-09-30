package com.chen.mvp.module.register.setPass;

import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.module.base.IBaseView;

/**
 * Created by long on 17-4-8.
 */

public interface ISetPassView extends IBaseView {

    /**
     * 加载信息
     */
    void loadInfo(BaseInfo info);

   // void showView();
}
