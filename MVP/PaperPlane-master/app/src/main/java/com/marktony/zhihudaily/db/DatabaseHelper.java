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

package com.marktony.zhihudaily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lizhaotailang on 2016/5/8.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists Zhihu("
                + "id integer primary key autoincrement,"
                + "zhihu_id integer not null,"
                + "zhihu_news text,"
                + "zhihu_time real,"
                + "zhihu_content text)");

        db.execSQL("create table if not exists Guokr("
                + "id integer primary key autoincrement,"
                + "guokr_id integer not null,"
                + "guokr_news text,"
                + "guokr_time real,"
                + "guokr_content text)");

        db.execSQL("create table if not exists Douban("
                + "id integer primary key autoincrement,"
                + "douban_id integer not null,"
                + "douban_news text,"
                + "douban_time real,"
                + "douban_content text)");

        db.execSQL("alter table Zhihu add column bookmark integer default 0");
        db.execSQL("alter table Guokr add column bookmark integer default 0");
        db.execSQL("alter table Douban add column bookmark integer default 0");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:

            case 2:
                // code of database creation version 3
                //  db.execSQL("create table if not exists LatestPosts("
                //+ "id integer primary key,"
                //        + "title text not null,"
                //        + "type integer not null,"
                //        + "img_url text not null,"
                //        + "date integer not null)");
                //db.execSQL("create table if not exists Contents("
                //        + "id integer primary key,"
                //        + "date integer not null,"
                //        + "content text not null)");
                db.execSQL("create table if not exists Contents(id integer primary key,date integer not null,content text not null)");

            case 3:
                // delete table if exists
                db.execSQL("delete from LatestPosts");
                db.execSQL("delete from Contents");
                db.execSQL("drop table if exists LatestPosts");
                db.execSQL("drop table if exists Contents");

                // create a new table of zhihu daily
                db.execSQL("create table if not exists Zhihu("
                        + "id integer primary key autoincrement,"
                        + "zhihu_id integer not null,"
                        + "zhihu_news text,"
                        + "zhihu_time integer,"
                        + "zhihu_content text)");

                db.execSQL("create table if not exists Guokr("
                        + "id integer primary key autoincrement,"
                        + "guokr_id integer not null,"
                        + "guokr_news text,"
                        + "guokr_time integer,"
                        + "guokr_content text)");

                db.execSQL("create table if not exists Douban("
                        + "id integer primary key autoincrement,"
                        + "douban_id integer not null,"
                        + "douban_news text,"
                        + "douban_time integer,"
                        + "douban_content text)");
            case 4:
                /**
                 * bookmark means if this obj is bookmarked
                 * it has two value, 0 and 1
                 * because SQLite doesn't has a boolean value
                 * so use the 0 and 1 value replaced
                 * 0 --> NOT MARKED, 1 ---> MARKED
                 * and when exit the app, the obj's whose bookmark value is 1
                 * should NOT be deleted.
                 */
                db.execSQL("alter table Zhihu add column bookmark integer default 0");
                db.execSQL("alter table Guokr add column bookmark integer default 0");
                db.execSQL("alter table Douban add column bookmark integer default 0");

        }
    }
}
