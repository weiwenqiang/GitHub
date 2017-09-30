package com.hpw.myapp.ui.tv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseFragment;
import com.hpw.mvpframe.utils.DisplayUtils;
import com.hpw.mvpframe.widget.GlideCircleTransform;
import com.hpw.mvpframe.widget.recyclerview.BaseMultiItemQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.mvpframe.widget.recyclerview.recyclerviewpager.LoopRecyclerViewPager;
import com.hpw.myapp.App;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.tv.activity.TvShowActivity;
import com.hpw.myapp.ui.tv.activity.TvShowFullActivity;
import com.hpw.myapp.ui.tv.contract.TvContract;
import com.hpw.myapp.ui.tv.model.FirstBannerBean;
import com.hpw.myapp.ui.tv.model.FirstBean;
import com.hpw.myapp.ui.tv.model.FirstModel;
import com.hpw.myapp.ui.tv.presenter.FirstPresenter;
import com.hpw.myapp.widget.GlideTransform;

import java.util.List;

/**
 * Created by hpw on 16/12/2.
 */
public class FirstFragment extends CoreBaseFragment<FirstPresenter, FirstModel> implements TvContract.FirstView {
    CoreRecyclerView coreRecyclerView;
    LoopRecyclerViewPager vpTop;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext)
                .init(new BaseQuickAdapter<FirstBean.RoomBean, BaseViewHolder>(R.layout.item_first_header) {
                    @Override
                    protected void convert(BaseViewHolder helper, FirstBean.RoomBean item) {
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 2);
                        layoutManager.setAutoMeasureEnabled(true);
                        helper.setText(R.id.category_name, item.getName());
                        ((CoreRecyclerView) helper.getView(R.id.recyclerview)).init(layoutManager, new MultipleItemAdapter(item.getList()), false);
                    }
                });
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

        View view1 = LayoutInflater.from(mContext).inflate(R.layout.daily_header, (ViewGroup) coreRecyclerView.getParent(), false);
        vpTop = (LoopRecyclerViewPager) view1.findViewById(R.id.vp_top);
        vpTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        coreRecyclerView.addHeaderView(view1);
    }

    @Override
    public void initData() {
        mPresenter.getFirstData();
        mPresenter.getBannerData();
        mPresenter.startInterval();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void doInterval(int i) {
        vpTop.smoothScrollToPosition(i);
    }

    @Override
    public void showContent(FirstBean info) {
        coreRecyclerView.getAdapter().addData(info.getRoom());
    }

    @Override
    public void showBannerContent(List<FirstBannerBean> info) {
        vpTop.setAdapter(new BaseQuickAdapter<FirstBannerBean, BaseViewHolder>(R.layout.item_first_banner, info) {
            @Override
            protected void convert(BaseViewHolder helper, FirstBannerBean item) {
                helper.setText(R.id.tv_top_title, item.getTitle());
                Glide.with(mContext).load(item.getThumb()).crossFade().into((ImageView) helper.getView(R.id.iv_top_image));
                helper.setOnClickListener(R.id.iv_top_image, v -> {
                    Intent starter = new Intent(mActivity, TvShowActivity.class);
                    starter.putExtra("playBean", item.getLink_object());
                    getActivity().startActivity(starter);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                });
            }
        });
    }

    private class MultipleItemAdapter extends BaseMultiItemQuickAdapter<FirstBean.RoomBean.ListBean, BaseViewHolder> {
        public MultipleItemAdapter(List<FirstBean.RoomBean.ListBean> data) {
            super(data);
            addItemType(FirstBean.RoomBean.ListBean.OTHER, R.layout.item_tv_other);
            addItemType(FirstBean.RoomBean.ListBean.OTHER1, R.layout.item_tv_other1);
        }

        @Override
        protected void convert(BaseViewHolder helper, FirstBean.RoomBean.ListBean item) {
            ViewGroup.LayoutParams lp = helper.getView(R.id.thumnails).getLayoutParams();
            lp.width = (App.SCREEN_WIDTH - DisplayUtils.dp2px(mContext, 12)) / 2;
            switch (helper.getItemViewType()) {
                case FirstBean.RoomBean.ListBean.OTHER:
                    lp.height = DisplayUtils.dp2px(mContext, 120);
                    Glide.with(mContext).load(item.getThumb()).crossFade().transform(new GlideTransform(mContext, 5)).into((ImageView) helper.getView(R.id.thumnails));
                    Glide.with(mContext).load(item.getAvatar()).crossFade().centerCrop().transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.ic_head));
                    helper.setText(R.id.title, item.getTitle())
                            .setText(R.id.tv_viewnum, item.getView())
                            .setText(R.id.nickName, item.getNick())
                            .setOnClickListener(R.id.ll_click, v -> {
                                Intent starter = new Intent(mActivity, TvShowActivity.class);
                                starter.putExtra("playBean", item);
                                getActivity().startActivity(starter);
                                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            });
                    break;
                case FirstBean.RoomBean.ListBean.OTHER1:
                    lp.height = lp.width;
                    Glide.with(mContext).load(item.getThumb()).crossFade().centerCrop().transform(new GlideTransform(mContext, 5)).into((ImageView) helper.getView(R.id.thumnails));
                    helper.setText(R.id.tv_viewnum, item.getView())
                            .setText(R.id.intro, item.getTitle())
                            .setOnClickListener(R.id.ll_click, v -> {
                                Intent starter = new Intent(mActivity, TvShowFullActivity.class);
                                starter.putExtra("playBean", item);
                                getActivity().startActivity(starter);
                                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            });
                    break;
            }
        }
    }
}