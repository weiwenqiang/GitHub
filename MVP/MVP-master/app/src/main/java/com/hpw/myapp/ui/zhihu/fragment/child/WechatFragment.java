package com.hpw.myapp.ui.zhihu.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseFragment;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.activity.WechatDetailsActivity;
import com.hpw.myapp.ui.zhihu.contract.ZhihuContract;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WXItemBean;
import com.hpw.myapp.ui.zhihu.model.wechatmodel.WechatModel;
import com.hpw.myapp.ui.zhihu.presenter.wechatpresenter.WechatPresenter;

import java.util.List;

/**
 * Created by hpw on 16/11/6.
 */

public class WechatFragment extends CoreBaseFragment<WechatPresenter, WechatModel> implements ZhihuContract.WechatView {
    CoreRecyclerView coreRecyclerView;
    private static int pageNum = 50;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext).init(new BaseQuickAdapter<WXItemBean, BaseViewHolder>(R.layout.item_weichat) {
            @Override
            protected void convert(BaseViewHolder helper, WXItemBean item) {
                Glide.with(mContext).load(item.getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.iv_wechat_item_image));
                helper.setText(R.id.tv_wechat_item_title, item.getTitle())
                        .setText(R.id.tv_wechat_item_from, item.getDescription())
                        .setText(R.id.tv_wechat_item_time, item.getCtime())
                        .setOnClickListener(R.id.ll_click, v -> {
                            WechatDetailsActivity.start(mContext, item.getTitle(), item.getUrl());
                        });
            }
        }).openLoadMore(pageNum, page -> mPresenter.getWechatData(pageNum, page))
                .openRefresh();
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showContent(List<WXItemBean> mList) {
        coreRecyclerView.getAdapter().addData(mList);
    }

    @Override
    public void showError(String msg) {
        showToast(msg + " api key失效,自己去http://www.tianapi.com/#wxnew申请key");
    }
}
