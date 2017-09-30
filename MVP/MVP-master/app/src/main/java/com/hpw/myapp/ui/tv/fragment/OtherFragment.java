package com.hpw.myapp.ui.tv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseFragment;
import com.hpw.mvpframe.utils.DisplayUtils;
import com.hpw.mvpframe.widget.GlideCircleTransform;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.myapp.App;
import com.hpw.myapp.Constants;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.tv.activity.TvShowActivity;
import com.hpw.myapp.ui.tv.contract.TvContract;
import com.hpw.myapp.ui.tv.model.OtherBean;
import com.hpw.myapp.ui.tv.model.OtherModel;
import com.hpw.myapp.ui.tv.presenter.OtherPresenter;
import com.hpw.myapp.widget.GlideTransform;

/**
 * Created by hpw on 16/12/2.
 */
public class OtherFragment extends CoreBaseFragment<OtherPresenter, OtherModel> implements TvContract.OtherView {
    CoreRecyclerView coreRecyclerView;

    public static OtherFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(Constants.ARG_OTHER_LIST_URL, url);
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        coreRecyclerView = new CoreRecyclerView(mContext).init(new GridLayoutManager(mContext, 2),
                new BaseQuickAdapter<OtherBean.DataBean, BaseViewHolder>(R.layout.item_tv_other) {
                    @Override
                    protected void convert(BaseViewHolder helper, OtherBean.DataBean item) {
                        //Glide在加载GridView等时,由于ImageView和Bitmap实际大小不符合,第一次时加载可能会变形(我这里出现了放大),必须在加载前再次固定ImageView大小
                        ViewGroup.LayoutParams lp = helper.getView(R.id.thumnails).getLayoutParams();
                        lp.width = (App.SCREEN_WIDTH - DisplayUtils.dp2px(mContext, 12)) / 2;
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
                    }
                })
                .openLoadMore(0, page -> mPresenter.getOtherData(getArguments().getString(Constants.ARG_OTHER_LIST_URL)))
                .openRefresh();
        return coreRecyclerView;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        mPresenter.getOtherData(getArguments().getString(Constants.ARG_OTHER_LIST_URL));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(OtherBean info) {
        coreRecyclerView.getAdapter().addData(info.getData());
    }
}
