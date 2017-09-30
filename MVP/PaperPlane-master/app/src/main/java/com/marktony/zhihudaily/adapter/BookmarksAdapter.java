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
import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.bean.ZhihuDailyNews;
import com.marktony.zhihudaily.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2016/12/22.
 */

public class BookmarksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<DoubanMomentNews.posts> doubanList;
    private List<GuokrHandpickNews.result> guokrList;
    private List<ZhihuDailyNews.Question> zhihuList;

    private List<Integer> types; // to store which type the item is.

    private OnRecyclerViewOnClickListener listener;

    public static final int TYPE_ZHIHU_NORMAL = 0;
    public static final int TYPE_ZHIHU_WITH_HEADER = 1;
    public static final int TYPE_GUOKR_NORMAL = 2;
    public static final int TYPE_GUOKR_WITH_HEADER = 3;
    public static final int TYPE_DOUBAN_NORMAL = 4;
    public static final int TYPE_DOUBAN_WITH_HEADER = 5;

    public BookmarksAdapter(@NonNull Context context,
                            ArrayList<ZhihuDailyNews.Question> zhihuList,
                            ArrayList<GuokrHandpickNews.result> guokrList,
                            ArrayList<DoubanMomentNews.posts> doubanList,
                            ArrayList<Integer> types) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        this.zhihuList = zhihuList;
        this.guokrList = guokrList;
        this.doubanList = doubanList;

        // types.size = zhihuList.size + guokrList.size + doubanList.size
        this.types = types;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ZHIHU_NORMAL:
            case TYPE_GUOKR_NORMAL:
            case TYPE_DOUBAN_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout, parent, false), this.listener);
        }
        return new WithTypeViewHolder(inflater.inflate(R.layout.bookmark_header, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (types.get(position)) {
            case TYPE_ZHIHU_WITH_HEADER:

                ((WithTypeViewHolder)holder).textViewType.setText(context.getResources().getString(R.string.zhihu_daily));

                break;

            case TYPE_ZHIHU_NORMAL:
                if (!zhihuList.isEmpty()) {
                    ZhihuDailyNews.Question q = zhihuList.get(position - 1);

                    Glide.with(context)
                            .load(q.getImages().get(0))
                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .error(R.drawable.placeholder)
                            .centerCrop()
                            .into(((NormalViewHolder)holder).imageView);

                    ((NormalViewHolder)holder).textViewTitle.setText(q.getTitle());
                }

                break;

            case TYPE_GUOKR_WITH_HEADER:

                ((WithTypeViewHolder)holder).textViewType.setText(R.string.guokr_handpick);

                break;

            case TYPE_GUOKR_NORMAL:

                if (!guokrList.isEmpty()) {
                    GuokrHandpickNews.result result = guokrList.get(position - zhihuList.size() - 2);

                    Glide.with(context)
                            .load(result.getHeadline_img_tb())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .error(R.drawable.placeholder)
                            .centerCrop()
                            .into(((NormalViewHolder) holder).imageView);

                    ((NormalViewHolder) holder).textViewTitle.setText(result.getTitle());
                }

                break;

            case TYPE_DOUBAN_WITH_HEADER:

                ((WithTypeViewHolder)holder).textViewType.setText(context.getResources().getString(R.string.douban_moment));

                break;
            case TYPE_DOUBAN_NORMAL:
                if (!doubanList.isEmpty()) {
                    DoubanMomentNews.posts post = doubanList.get(position - zhihuList.size() - guokrList.size() - 3);

                    if (post.getThumbs().size() == 0) {
                        ((NormalViewHolder)holder).imageView.setVisibility(View.INVISIBLE);
                    } else {
                        Glide.with(context)
                                .load(post.getThumbs().get(0).getMedium().getUrl())
                                .asBitmap()
                                .placeholder(R.drawable.placeholder)
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .error(R.drawable.placeholder)
                                .centerCrop()
                                .into(((NormalViewHolder)holder).imageView);
                    }

                    ((NormalViewHolder)holder).textViewTitle.setText(post.getTitle());
                }

                break;

            default:
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public void setItemListener(OnRecyclerViewOnClickListener listener) {
        this.listener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView imageView;
        TextView textViewTitle;
        OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewCover);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }

    public class WithTypeViewHolder extends RecyclerView.ViewHolder{

        TextView textViewType;

        public WithTypeViewHolder(View itemView) {
            super(itemView);
            textViewType = (TextView) itemView.findViewById(R.id.textViewType);
        }

    }

}
