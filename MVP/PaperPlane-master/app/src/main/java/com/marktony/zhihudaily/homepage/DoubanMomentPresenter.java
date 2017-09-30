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

package com.marktony.zhihudaily.homepage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.bean.StringModelImpl;
import com.marktony.zhihudaily.db.DatabaseHelper;
import com.marktony.zhihudaily.detail.DetailActivity;
import com.marktony.zhihudaily.interfaze.OnStringListener;
import com.marktony.zhihudaily.service.CacheService;
import com.marktony.zhihudaily.util.Api;
import com.marktony.zhihudaily.bean.BeanType;
import com.marktony.zhihudaily.util.DateFormatter;
import com.marktony.zhihudaily.util.NetworkState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Lizhaotailang on 2016/9/10.
 */

public class DoubanMomentPresenter implements DoubanMomentContract.Presenter {

    private DoubanMomentContract.View view;
    private Context context;
    private StringModelImpl model;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Gson gson = new Gson();

    private ArrayList<DoubanMomentNews.posts> list = new ArrayList<>();

    public DoubanMomentPresenter(Context context, DoubanMomentContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);
        dbHelper = new DatabaseHelper(context, "History.db", null, 5);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void startReading(int position) {
        DoubanMomentNews.posts item = list.get(position);
        Intent intent = new Intent(context, DetailActivity.class);

        intent.putExtra("type", BeanType.TYPE_DOUBAN);
        intent.putExtra("id", item.getId());
        intent.putExtra("title", item.getTitle());
        if (item.getThumbs().size() == 0){
            intent.putExtra("coverUrl", "");
        } else {
            intent.putExtra("coverUrl", item.getThumbs().get(0).getMedium().getUrl());
        }
        context.startActivity(intent);
    }

    @Override
    public void loadPosts(long date, final boolean clearing) {

        if (clearing) {
            view.startLoading();
        }

        if (NetworkState.networkConnected(context)) {

            model.load(Api.DOUBAN_MOMENT + new DateFormatter().DoubanDateFormat(date), new OnStringListener() {
                @Override
                public void onSuccess(String result) {

                    try {
                        DoubanMomentNews post = gson.fromJson(result, DoubanMomentNews.class);
                        ContentValues values = new ContentValues();

                        if (clearing) {
                            list.clear();
                        }

                        for (DoubanMomentNews.posts item : post.getPosts()) {

                            list.add(item);

                            if ( !queryIfIDExists(item.getId())) {
                                db.beginTransaction();
                                try {
                                    values.put("douban_id", item.getId());
                                    values.put("douban_news", gson.toJson(item));
                                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = format.parse(item.getPublished_time());
                                    values.put("douban_time", date.getTime() / 1000);
                                    values.put("douban_content", "");
                                    db.insert("Douban", null, values);
                                    values.clear();
                                    db.setTransactionSuccessful();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    db.endTransaction();
                                }
                            }
                            Intent intent = new Intent("com.marktony.zhihudaily.LOCAL_BROADCAST");
                            intent.putExtra("type", CacheService.TYPE_DOUBAN);
                            intent.putExtra("id", item.getId());
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                        view.showResults(list);
                    } catch (JsonSyntaxException e) {
                        view.showLoadingError();
                    }

                    view.stopLoading();

                }

                @Override
                public void onError(VolleyError error) {
                    view.stopLoading();
                    view.showLoadingError();
                }
            });
        } else {

            if (clearing) {

                list.clear();

                Cursor cursor = db.query("Douban", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        DoubanMomentNews.posts post = gson.fromJson(cursor.getString(cursor.getColumnIndex("douban_news")), DoubanMomentNews.posts.class);
                        list.add(post);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                view.stopLoading();
                view.showResults(list);
            }
        }
    }

    @Override
    public void refresh() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date, false);
    }

    @Override
    public void feelLucky() {
        if (list.isEmpty()) {
            view.showLoadingError();
            return;
        }
        startReading(new Random().nextInt(list.size()));
    }

    @Override
    public void start() {
        refresh();
    }

    private boolean queryIfIDExists(int id){
        Cursor cursor = db.query("Douban",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                if (id == cursor.getInt(cursor.getColumnIndex("douban_id"))){
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return false;
    }

}
