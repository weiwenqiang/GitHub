package com.lcodecore.twinklingrefreshlayout.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.twinklingrefreshlayout.R;
import com.lcodecore.twinklingrefreshlayout.adapter.base.BaseRecyclerAdapter;
import com.lcodecore.twinklingrefreshlayout.adapter.base.CommonHolder;
import com.lcodecore.twinklingrefreshlayout.beans.Food;
import com.lcodecore.twinklingrefreshlayout.beans.Photo;

import butterknife.BindView;

/**
 * Created by lcodecore on 2016/12/7.
 */

public class PhotoAdapter extends BaseRecyclerAdapter<Photo> {
    @Override
    public CommonHolder<Photo> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Photo> {

        @BindView(R.id.tv_info)
        TextView tv_info;

        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_photo);
        }

        @Override
        public void bindData(Photo photo) {
            iv_pic.setImageResource(photo.imgSrc);
            tv_info.setText(photo.name);
        }
    }
}