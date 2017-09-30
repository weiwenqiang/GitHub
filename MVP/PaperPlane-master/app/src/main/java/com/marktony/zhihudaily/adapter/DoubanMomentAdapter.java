/*
 * Copyright 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marktony.zhihudaily.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhaotailang on 2016/8/11.
 */

public class DoubanMomentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<DoubanMomentNews.posts> list;

    private static final int TYPE_NORMAL = 0x00;
    private static final int TYPE_FOOTER = 0x02;
    private static final int TYPE_NO_IMG = 0x03;

    private OnRecyclerViewOnClickListener listener;

    public DoubanMomentAdapter(@NonNull Context context, @NonNull ArrayList<DoubanMomentNews.posts> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout, parent, false), listener);
            case TYPE_NO_IMG:
                return new NoImgViewHolder(inflater.inflate(R.layout.home_list_item_without_image, parent, false), listener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if ( !(holder instanceof FooterViewHolder)) {
            DoubanMomentNews.posts item = list.get(position);
            if (holder instanceof NormalViewHolder) {

                Glide.with(context)
                        .load(item.getThumbs().get(0).getMedium().getUrl())
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((NormalViewHolder)holder).ivHeadlineImg);

                ((NormalViewHolder)holder).tvTitle.setText(item.getTitle());

            } else if (holder instanceof NoImgViewHolder) {

                ((NoImgViewHolder)holder).tvTitle.setText(item.getTitle());

            }
        }

        // handle the footer

    }


    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        }
        if (list.get(position).getThumbs().size() == 0) {
            return TYPE_NO_IMG;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.listener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivHeadlineImg;
        TextView tvTitle;

        OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            ivHeadlineImg = (ImageView) itemView.findViewById(R.id.imageViewCover);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.OnItemClick(view, getLayoutPosition());
            }
        }
    }

    public class NoImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;

        OnRecyclerViewOnClickListener listener;

        public NoImgViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.OnItemClick(view, getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
