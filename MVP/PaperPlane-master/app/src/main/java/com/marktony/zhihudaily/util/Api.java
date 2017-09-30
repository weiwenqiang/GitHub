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

package com.marktony.zhihudaily.util;

/**
 * Created by lizhaotailang on 2016/3/18.
 * 包含了主要的api接口
 * this class contain the main APIs
 */
public class Api {

    // 所有的知乎日报API的HTTP METHOD 均为GET
    // the method of all zhihu daily's api is GET

    // 知乎日报base url,将文章id拼接值base url之后即可
    // ZhihuDaily base url, used in browser, add the post id to it
    // public static final String ZHIHU_DAILY_BASE_URL = "http://news-at.zhihu.com/story/";

    // 获取界面启动图像
    // get the open screen page image url
    // start_image后面为图像分辨率
    // The end of start_image is the open screen image's resolution
    // public static final String START_IMAGE = "http://news-at.zhihu.com/api/4/start-image/1080*1776";

    // 最新消息
    // latest posts
    // ZHIHU_NEWS API替代，拼接当日日期后可以获取
    // public static final String LATEST = "http://news-at.zhihu.com/api/4/news/latest";

    // 消息内容获取与离线下载
    // content of post and download offline
    // 在最新消息中获取到的id，拼接到这个NEWS之后，可以获得对应的JSON格式的内容
    // add the id that you got from latest post to ZHIHU_NEWS and you will get the content as json format
    public static final String ZHIHU_NEWS = "http://news-at.zhihu.com/api/4/news/";

    // 过往消息
    // past posts
    // 若要查询的11月18日的消息，before后面的数字应该为20161118
    // if you want to select the posts of November 11th, the number after 'before' should be 20161118
    // 知乎日报的生日为2013 年 5 月 19 日，如果before后面的数字小于20130520，那么只能获取到空消息
    // the birthday of ZhiHuDaily is May 19th, 2013. So if the number is lower than 20130520, you will get a null value of post
    public static final String ZHIHU_HISTORY = "http://news.at.zhihu.com/api/4/news/before/";

    // 新闻额外消息
    // extra content of post
    // 输入新闻的ID，获取对应新闻的额外信息，如评论数量，所获的『赞』的数量。
    // add the post id then you can get the extra info, like the comment account and the like account
    // example:http://news-at.zhihu.com/api/4/story-extra/#{id}
    // public static final String STORY_EXTRA = "http://news-at.zhihu.com/api/4/story-extra/";

    // 新闻对应长评论查看
    // long comments of post
    // 使用在 最新消息 中获得的 id
    // use the post id that you got in latest posts
    // 在 http://news-at.zhihu.com/api/4/story/#{id}/long-comments 中将 id 替换为对应的 id
    // replace id
    // 得到长评论 JSON 格式的内容
    // get the long comment as json format
    // 新闻对应短评论查看
    // short comment of post
    // http://news-at.zhihu.com/api/4/story/4232852/short-comments
    // 使用在 最新消息 中获得的 id
    // use the post id that you got in latest posts
    // 在 http://news-at.zhihu.com/api/4/story/#{id}/short-comments 中将 id 替换为对应的 id
    // replace id
    // 得到短评论 JSON 格式的内容
    // get the short comment as json format
    // public static final String COMMENTS = "http://news-at.zhihu.com/api/4/story/";

    // 主题日报列表查看
    // Theme posts
    // public static final String THEMES = "http://news-at.zhihu.com/api/4/themes";

    // 主题日报内容查看
    // check out the content of theme post
    // http://news-at.zhihu.com/api/4/theme/11
    // 使用在 主题日报列表查看 中获得需要查看的主题日报的 id
    // similarly, use the id you got in theme post list
    // 拼接在 http://news-at.zhihu.com/api/4/theme/ 后
    // add it to http://news-at.zhihu.com/api/4/theme/
    // 得到对应主题日报 JSON 格式的内容
    // just like the latest post, add the id you got in theme post, and u will get the content as json format
    // public static final String THEME = "http://news-at.zhihu.com/api/4/theme/";

    // 热门消息
    // hot posts
    // 请注意！ 此 API 仍可访问，但是其内容未出现在最新的『知乎日报』 App 中。
    // Please pay attention to this api. It is accessible yet, but it doesn't appear in the ZhihuDaily APP.
    // public static final String HOT = "http://news-at.zhihu.com/api/3/news/hot";

    // 查看新闻的推荐者
    // checkout the recommenders
    // "http://news-at.zhihu.com/api/4/story/#{id}/recommenders"
    // 将新闻id填入到#{id}的位置
    // replace the #{id} with the id you got.

    // 获取某个专栏之前的新闻
    // acquire the past posts of one column
    // http://news-at.zhihu.com/api/4/theme/#{theme id}/before/#{id}
    // 将专栏id填入到 #{theme id}, 将新闻id填入到#{id}
    // put column id into #{theme id}, put post id into #{id}
    // 如 http://news-at.zhihu.com/api/4/theme/11/before/7119483
    // just like http://news-at.zhihu.com/api/4/theme/11/before/7119483
    // 注：新闻id要是属于该专栏，否则，返回结果为空
    // attention: the post id must belong to that column, or you will got a null value

    // 查看editor的主页
    // check out the home page of editor
    // http://news-at.zhihu.com/api/4/editor/#{id}/profile-page/android

    // Guokr base url
    // public static final String GUOKR_ARTICLE_BASE_URL = "http://apis.guokr.com/handpick/article.json";

    // 获取果壳精选的文章列表,通过组合相应的参数成为完整的url
    // Guokr handpick articles. make complete url by combining params
    public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    // 获取果壳文章的具体信息 V1
    // specific information of guokr post V1
    public static final String GUOKR_ARTICLE_LINK_V1 = "http://jingxuan.guokr.com/pick/";

    // 获取果壳文章的具体信息 V2
    // V2
    // public static final String GUOKR_ARTICLE_LINK_V2 = "http://jingxuan.guokr.com/pick/v2/";

    // 获取果壳精选的轮播文章列表
    // carousel posts
    // public static final String GUOKR_HANDPICK_CAROUSEL = "http://apis.guokr.com/flowingboard/item/handpick_carousel.json";

    // 豆瓣一刻
    // 根据日期查询消息列表
    // eg:https://moment.douban.com/api/stream/date/2016-08-11
    public static final String DOUBAN_MOMENT = "https://moment.douban.com/api/stream/date/";

    // 获取文章具体内容
    // eg:https://moment.douban.com/api/post/100484
    public static final String DOUBAN_ARTICLE_DETAIL = "https://moment.douban.com/api/post/";

}
