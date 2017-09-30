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

package com.marktony.zhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/17.
 */

public class DoubanMomentStory {

    private int display_style;
    private String short_url;
    @SerializedName("abstract")
    private String abs;
    private int app_css;
    private int like_count;
    private ArrayList<DoubanMomentNews.posts.thumbs> thumbs;
    private String created_time;
    private int id;
    private boolean is_editor_choice;
    private String original_url;
    private String content;
    private String share_pic_url;
    private String type;
    private boolean is_liked;
    private ArrayList<DoubanMomentNews.posts.thumbs> photos;
    private String published_time;
    private String url;
    private String column;
    private int comments_count;
    private String title;

    public int getDisplay_style() {
        return display_style;
    }

    public void setDisplay_style(int display_style) {
        this.display_style = display_style;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public int getApp_css() {
        return app_css;
    }

    public void setApp_css(int app_css) {
        this.app_css = app_css;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public ArrayList<DoubanMomentNews.posts.thumbs> getThumbs() {
        return thumbs;
    }

    public void setThumbs(ArrayList<DoubanMomentNews.posts.thumbs> thumbs) {
        this.thumbs = thumbs;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean is_editor_choice() {
        return is_editor_choice;
    }

    public void setIs_editor_choice(boolean is_editor_choice) {
        this.is_editor_choice = is_editor_choice;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShare_pic_url() {
        return share_pic_url;
    }

    public void setShare_pic_url(String share_pic_url) {
        this.share_pic_url = share_pic_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean is_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public ArrayList<DoubanMomentNews.posts.thumbs> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<DoubanMomentNews.posts.thumbs> photos) {
        this.photos = photos;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
