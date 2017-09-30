package com.hpw.myapp.ui.zhihu.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseLazyFragment;
import com.hpw.mvpframe.widget.GlideCircleTransform;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.mvpframe.widget.recyclerview.listener.OnItemClickListener;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.model.quick.QuickModel;
import com.hpw.myapp.ui.zhihu.presenter.quickpresenter.QuickPresenter;
import com.hpw.myapp.ui.zhihu.model.quick.Status;

/**
 * Created by hpw on 16/11/1.
 */

public class QuickFragment extends CoreBaseLazyFragment<QuickPresenter, QuickModel> {
    CoreRecyclerView coreRecyclerView;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext)
                .init(new BaseQuickAdapter<Status, BaseViewHolder>(R.layout.item_tweet) {
                    @Override
                    protected void convert(BaseViewHolder helper, Status item) {
                        helper.setText(R.id.tweetName, item.getUserName())
                                .setText(R.id.tweetText, item.getText())
                                .setText(R.id.tweetDate, item.getCreatedAt())
                                .setVisible(R.id.tweetRT, item.isRetweet())
                                .addOnClickListener(R.id.tweetAvatar)
                                .addOnClickListener(R.id.tweetName)
                                .linkify(R.id.tweetText);

                        Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
                    }
                })
                .addOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                        showToast("点击了" + position);
                    }
                })
                .openLoadMore(10, page -> coreRecyclerView.getAdapter().addData(mModel.getData()))
                .openRefresh();
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        coreRecyclerView.getAdapter().addData(mModel.getData());
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {

    }
}
