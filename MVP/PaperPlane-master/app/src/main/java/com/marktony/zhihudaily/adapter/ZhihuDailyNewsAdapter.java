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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.marktony.zhihudaily.bean.ZhihuDailyNews;
import com.marktony.zhihudaily.interfaze.OnRecyclerViewOnClickListener;
import com.marktony.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2016/3/18.
 * 知乎日报消息适配器
 * latest posts adapter
 */
public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<ZhihuDailyNews.Question> list = new ArrayList<ZhihuDailyNews.Question>();
    private OnRecyclerViewOnClickListener mListener;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 1;

    public ZhihuDailyNewsAdapter(Context context, List<ZhihuDailyNews.Question> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NormalViewHolder) {

            ZhihuDailyNews.Question item = list.get(position);

            if (item.getImages().get(0) == null){
                ((NormalViewHolder)holder).itemImg.setImageResource(R.drawable.placeholder);
            } else {
                Glide.with(context)
                        .load(item.getImages().get(0))
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((NormalViewHolder)holder).itemImg);
            }
            ((NormalViewHolder)holder).tvLatestNewsTitle.setText(item.getTitle());
        }
        // handle the footer view holder
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return ZhihuDailyNewsAdapter.TYPE_FOOTER;
        }
        return ZhihuDailyNewsAdapter.TYPE_NORMAL;
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImg;
        private TextView tvLatestNewsTitle;
        private OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            itemImg = (ImageView) itemView.findViewById(R.id.imageViewCover);
            tvLatestNewsTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

    }

}
