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
import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.bean.StringModelImpl;
import com.marktony.zhihudaily.db.DatabaseHelper;
import com.marktony.zhihudaily.detail.DetailActivity;
import com.marktony.zhihudaily.interfaze.OnStringListener;
import com.marktony.zhihudaily.service.CacheService;
import com.marktony.zhihudaily.util.Api;
import com.marktony.zhihudaily.bean.BeanType;
import com.marktony.zhihudaily.util.NetworkState;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lizhaotailang on 2016/9/15.
 */

public class GuokrPresenter implements GuokrContract.Presenter {

    private GuokrContract.View view;
    private Context context;
    private StringModelImpl model;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private ArrayList<GuokrHandpickNews.result> list = new ArrayList<GuokrHandpickNews.result>();
    private Gson gson = new Gson();

    public GuokrPresenter(Context context, GuokrContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
        model = new StringModelImpl(context);
        dbHelper = new DatabaseHelper(context, "History.db", null, 5);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void startReading(int position) {
        GuokrHandpickNews.result item = list.get(position);
        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_GUOKR)
                .putExtra("id", item.getId())
                .putExtra("coverUrl", item.getHeadline_img())
                .putExtra("title", item.getTitle())
        );
    }

    @Override
    public void feelLucky() {
        if (list.isEmpty()) {
            view.showError();
            return;
        }
        startReading(new Random().nextInt(list.size()));
    }

    @Override
    public void start() {
        loadPosts();
    }

    @Override
    public void loadPosts() {

        view.showLoading();

        if (NetworkState.networkConnected(context)) {
            model.load(Api.GUOKR_ARTICLES, new OnStringListener() {
                @Override
                public void onSuccess(String result) {

                    // 由于果壳并没有按照日期加载的api
                    // 所以不存在加载指定日期内容的操作，当要请求数据时一定是在进行刷新
                    list.clear();

                    try {

                        GuokrHandpickNews question = gson.fromJson(result, GuokrHandpickNews.class);

                        for (GuokrHandpickNews.result re : question.getResult()){

                            list.add(re);

                            if(!queryIfIDExists(re.getId())) {
                                try {
                                    db.beginTransaction();
                                    ContentValues values = new ContentValues();
                                    values.put("guokr_id", re.getId());
                                    values.put("guokr_news", gson.toJson(re));
                                    values.put("guokr_content", "");
                                    values.put("guokr_time", (long)re.getDate_picked());
                                    db.insert("Guokr", null, values);
                                    values.clear();
                                    db.setTransactionSuccessful();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    db.endTransaction();
                                }

                            }

                            Intent intent = new Intent("com.marktony.zhihudaily.LOCAL_BROADCAST");
                            intent.putExtra("type", CacheService.TYPE_GUOKR);
                            intent.putExtra("id", re.getId());
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                        }
                        view.showResults(list);

                    } catch (JsonSyntaxException e) {
                        view.showError();
                    }

                    view.stopLoading();

                }

                @Override
                public void onError(VolleyError error) {
                    view.stopLoading();
                    view.showError();
                }
            });
        } else {

            Cursor cursor = db.query("Guokr", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    GuokrHandpickNews.result result = gson.fromJson(cursor.getString(cursor.getColumnIndex("guokr_news")), GuokrHandpickNews.result.class);
                    list.add(result);
                } while (cursor.moveToNext());
            }
            cursor.close();
            view.stopLoading();
            view.showResults(list);
            //当第一次安装应用，并且没有打开网络时
            //此时既无法网络加载，也无法本地加载
            if (list.isEmpty()) {
                view.showError();
            }
        }
    }

    @Override
    public void refresh() {
        loadPosts();
    }

    private boolean queryIfIDExists(int id){
        Cursor cursor = db.query("Guokr",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                if (id == cursor.getInt(cursor.getColumnIndex("guokr_id"))){
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return false;
    }

}
