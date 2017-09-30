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
 * Created by Lizhaotailang on 2016/9/16.
 */

public class DoubanMomentNews {

    private int count;
    private ArrayList<posts> posts;
    private int offset;
    private String date;
    private int total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<DoubanMomentNews.posts> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<DoubanMomentNews.posts> posts) {
        this.posts = posts;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public class posts {

        private int display_style;
        private boolean is_editor_choice;
        private String published_time;
        private String url;
        private String short_url;
        private boolean is_liked;
        private author author;
        private String column;
        private int app_css;
        @SerializedName("abstract")
        private String abs;
        private String date;
        private int like_count;
        private int comments_count;
        private ArrayList<thumbs> thumbs;
        private String created_time;
        private String title;
        private String share_pic_url;
        private String type;
        private int id;

        public class thumbs {
            medium medium;
            String description;
            large large;
            String tag_name;
            small small;
            int id;

            public DoubanMomentNews.posts.medium getMedium() {
                return medium;
            }

            public void setMedium(DoubanMomentNews.posts.medium medium) {
                this.medium = medium;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public DoubanMomentNews.posts.large getLarge() {
                return large;
            }

            public void setLarge(DoubanMomentNews.posts.large large) {
                this.large = large;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public DoubanMomentNews.posts.small getSmall() {
                return small;
            }

            public void setSmall(DoubanMomentNews.posts.small small) {
                this.small = small;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public class small {
            String url;
            int width;
            int height;

            public String getUrl() {
                return url;
            }
        }

        public class medium {
            String url;
            int width;
            int height;

            public String getUrl() {
                return url;
            }
        }

        public class large {
            String url;
            int width;
            int height;

            public String getUrl() {
                return url;
            }
        }

        public int getDisplay_style() {
            return display_style;
        }

        public void setDisplay_style(int display_style) {
            this.display_style = display_style;
        }

        public boolean is_editor_choice() {
            return is_editor_choice;
        }

        public void setIs_editor_choice(boolean is_editor_choice) {
            this.is_editor_choice = is_editor_choice;
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

        public String getShort_url() {
            return short_url;
        }

        public void setShort_url(String short_url) {
            this.short_url = short_url;
        }

        public boolean is_liked() {
            return is_liked;
        }

        public void setIs_liked(boolean is_liked) {
            this.is_liked = is_liked;
        }

        public DoubanMomentNews.author getAuthor() {
            return author;
        }

        public void setAuthor(DoubanMomentNews.author author) {
            this.author = author;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public int getApp_css() {
            return app_css;
        }

        public void setApp_css(int app_css) {
            this.app_css = app_css;
        }

        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public ArrayList<thumbs> getThumbs() {
            return thumbs;
        }

        public void setThumbs(ArrayList<thumbs> thumbs) {
            this.thumbs = thumbs;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private class author {
        boolean is_followed;
        String uid;
        String url;
        String avatar;
        String name;
        boolean is_special_user;
        int n_posts;
        String alt;
        String large_avatar;
        String id;
        boolean is_auth_author;
    }

}
