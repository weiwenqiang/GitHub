package com.hpw.myapp.ui.zhihu.contract;

import com.hpw.mvpframe.base.CoreBaseModel;
import com.hpw.mvpframe.base.CoreBasePresenter;
import com.hpw.mvpframe.base.CoreBaseView;
import com.hpw.myapp.ui.zhihu.model.quick.Status;

import java.util.List;

/**
 * Created by hpw on 16/11/1.
 */

public interface QuickContract {
    interface Model extends CoreBaseModel {
        List<Status> getData();
    }

    interface View extends CoreBaseView {

    }

    abstract class Presenter extends CoreBasePresenter<Model, View> {

    }
}
