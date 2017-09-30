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

package com.marktony.zhihudaily.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.marktony.zhihudaily.app.VolleySingleton;
import com.marktony.zhihudaily.bean.ZhihuDailyStory;
import com.marktony.zhihudaily.db.DatabaseHelper;
import com.marktony.zhihudaily.util.Api;

import java.util.Calendar;

/**
 * Created by Lizhaotailang on 2016/9/18.
 * 定义了内部类LocalReceiver， 用于接收内部广播并获取传递的数据
 * 可以将三种请求内容的方法合并为一个，但是这样会使代码可读性变差
 */

public class CacheService extends Service {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private static final String TAG = CacheService.class.getSimpleName();

    public static final int TYPE_ZHIHU = 0x00;
    public static final int TYPE_GUOKR = 0x01;
    public static final int TYPE_DOUBAN = 0x02;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DatabaseHelper(this, "History.db", null, 5);
        db = dbHelper.getWritableDatabase();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.marktony.zhihudaily.LOCAL_BROADCAST");
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(new LocalReceiver(), filter);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 网络请求id对应的知乎日报的内容主体
     * 当type为0时，存储body中的数据
     * 当type为1时，再次请求share url中的内容并储存
     * @param id 所要获取的知乎日报消息内容对应的id
     */
    private void startZhihuCache(final int id) {

        Cursor cursor = db.query("Zhihu", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getInt(cursor.getColumnIndex("zhihu_id")) == id)
                        && (cursor.getString(cursor.getColumnIndex("zhihu_content")).equals(""))) {
                    StringRequest request = new StringRequest(Request.Method.GET, Api.ZHIHU_NEWS + id, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Gson gson = new Gson();
                            ZhihuDailyStory story = gson.fromJson(s, ZhihuDailyStory.class);
                            if (story.getType() == 1) {
                                StringRequest request = new StringRequest(Request.Method.GET, story.getShare_url(), new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        ContentValues values = new ContentValues();
                                        values.put("zhihu_content", s);
                                        db.update("Zhihu", values, "zhihu_id = ?", new String[] {String.valueOf(id)});
                                        values.clear();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                });
                                request.setTag(TAG);
                                VolleySingleton.getVolleySingleton(CacheService.this).addToRequestQueue(request);
                            } else {
                                ContentValues values = new ContentValues();
                                values.put("zhihu_content", s);
                                db.update("Zhihu", values, "zhihu_id = ?", new String[] {String.valueOf(id)});
                                values.clear();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });
                    request.setTag(TAG);
                    VolleySingleton.getVolleySingleton(CacheService.this).addToRequestQueue(request);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    /**
     * 网络请求豆瓣精选的内容主体并储存
     * @param id 消息对应的id
     */
    private void startDoubanCache(final int id) {

        Cursor cursor = db.query("Douban", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getInt(cursor.getColumnIndex("douban_id")) == id)
                        && (cursor.getString(cursor.getColumnIndex("douban_content")).equals(""))) {

                    StringRequest request = new StringRequest(Api.DOUBAN_ARTICLE_DETAIL + id, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            ContentValues values = new ContentValues();
                            values.put("douban_content", s);
                            db.update("Douban", values, "douban_id = ?", new String[] {String.valueOf(id)});
                            values.clear();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });
                    request.setTag(TAG);
                    VolleySingleton.getVolleySingleton(CacheService.this).getRequestQueue().add(request);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    /**
     * 网络请求果壳的消息内容主体并存储
     * @param id 对应的id
     */
    private void startGuokrCache(final int id) {
        Cursor cursor = db.query("Guokr", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getInt(cursor.getColumnIndex("guokr_id")) == id)
                        && (cursor.getString(cursor.getColumnIndex("guokr_content")).equals(""))) {
                    StringRequest request = new StringRequest(Api.GUOKR_ARTICLE_LINK_V1 + id, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            ContentValues values = new ContentValues();
                            values.put("guokr_content", s);
                            db.update("Guokr", values, "guokr_id = ?", new String[] {String.valueOf(id)});
                            values.clear();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });
                    request.setTag(TAG);
                    VolleySingleton.getVolleySingleton(CacheService.this).getRequestQueue().add(request);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        VolleySingleton.getVolleySingleton(this).getRequestQueue().cancelAll(TAG);
        deleteTimeoutPosts();
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra("id", 0);
            switch (intent.getIntExtra("type", -1)) {
                case TYPE_ZHIHU:
                    startZhihuCache(id);
                    break;
                case TYPE_GUOKR:
                    startGuokrCache(id);
                    break;
                case TYPE_DOUBAN:
                    startDoubanCache(id);
                    break;
                default:
                case -1:
                    break;
            }
        }
    }

    private void deleteTimeoutPosts(){

        SharedPreferences sp = getSharedPreferences("user_settings",MODE_PRIVATE);

        Calendar c = Calendar.getInstance();
        long timeStamp = (c.getTimeInMillis() / 1000) - Long.parseLong(sp.getString("time_of_saving_articles", "7"))*24*60*60;

        String[] whereArgs = new String[] {String.valueOf(timeStamp)};
        db.delete("Zhihu", "zhihu_time < ? and bookmark != 1", whereArgs);
        db.delete("Guokr", "guokr_time < ? and bookmark != 1", whereArgs);
        db.delete("Douban", "douban_time < ? and bookmark != 1", whereArgs);

    }

}
