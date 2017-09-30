package com.lcodecore.twinklingrefreshlayout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.twinklingrefreshlayout.R;
import com.lcodecore.twinklingrefreshlayout.beans.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcodecore on 2016/12/7.
 */

public class ScienceAdapter extends BaseAdapter {

    private List<Card> cards = new ArrayList<>();

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_science, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(cards.get(position).title);
        holder.iv_cover.setImageResource(cards.get(position).imageSrc);

        return convertView;
    }

    class ViewHolder {
        final ImageView iv_cover;
        final TextView tv_name;

        ViewHolder(View view) {
            iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public void refreshCard() {
        cards.clear();
        cards.add(new Card("genetics", "", R.drawable.science1));
        cards.add(new Card("globe", "", R.drawable.science2));
        cards.add(new Card("lab-flask-leaf", "", R.drawable.science3));
        cards.add(new Card("magnet", "", R.drawable.science4));
        cards.add(new Card("microscope", "", R.drawable.science5));
        cards.add(new Card("moon", "", R.drawable.science6));
        cards.add(new Card("telescope", "", R.drawable.science7));
        cards.add(new Card("satellite", "", R.drawable.science8));
        cards.add(new Card("Newtons-cradle", "", R.drawable.science9));
        cards.add(new Card("nuclear-symbol", "", R.drawable.science10));
        notifyDataSetChanged();
    }

    public void loadMoreCard() {
        cards.add(new Card("genetics", "", R.drawable.science1));
        cards.add(new Card("globe", "", R.drawable.science2));
        cards.add(new Card("lab-flask-leaf", "", R.drawable.science3));
        cards.add(new Card("magnet", "", R.drawable.science4));
        cards.add(new Card("microscope", "", R.drawable.science5));
        cards.add(new Card("moon", "", R.drawable.science6));
        cards.add(new Card("telescope", "", R.drawable.science7));
        cards.add(new Card("satellite", "", R.drawable.science8));
        cards.add(new Card("Newtons-cradle", "", R.drawable.science9));
        cards.add(new Card("nuclear-symbol", "", R.drawable.science10));
        notifyDataSetChanged();
    }
}