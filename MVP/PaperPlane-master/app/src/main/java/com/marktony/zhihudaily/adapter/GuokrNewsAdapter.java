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
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2016/6/14.
 */
public class GuokrNewsAdapter extends RecyclerView.Adapter<GuokrNewsAdapter.GuokrPostViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<GuokrHandpickNews.result> list;

    private OnRecyclerViewOnClickListener mListener;

    public GuokrNewsAdapter(Context context, ArrayList<GuokrHandpickNews.result> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public GuokrPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.home_list_item_layout,parent,false);

        return new GuokrPostViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(GuokrPostViewHolder holder, int position) {

        GuokrHandpickNews.result item = list.get(position);

        Glide.with(context)
                .load(item.getHeadline_img_tb())
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(holder.ivHeadlineImg);

        holder.tvTitle.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class GuokrPostViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        ImageView ivHeadlineImg;
        TextView tvTitle;

        OnRecyclerViewOnClickListener listener;

        public GuokrPostViewHolder(View itemView,OnRecyclerViewOnClickListener listener) {
            super(itemView);

            ivHeadlineImg = (ImageView) itemView.findViewById(R.id.imageViewCover);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);

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

}
