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

package com.marktony.zhihudaily.detail;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.text.Html;
import android.webkit.WebView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.bean.DoubanMomentStory;
import com.marktony.zhihudaily.bean.StringModelImpl;
import com.marktony.zhihudaily.bean.ZhihuDailyStory;
import com.marktony.zhihudaily.customtabs.CustomFallback;
import com.marktony.zhihudaily.customtabs.CustomTabActivityHelper;
import com.marktony.zhihudaily.db.DatabaseHelper;
import com.marktony.zhihudaily.interfaze.OnStringListener;
import com.marktony.zhihudaily.util.Api;
import com.marktony.zhihudaily.bean.BeanType;
import com.marktony.zhihudaily.util.NetworkState;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lizhaotailang on 2016/12/27.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;
    private StringModelImpl model;
    private Context context;

    private ZhihuDailyStory zhihuDailyStory;
    private String guokrStory;
    private DoubanMomentStory doubanMomentStory;

    private SharedPreferences sp;
    private DatabaseHelper dbHelper;

    private Gson gson;

    // the four data come from the intent extra
    private BeanType type;
    private int id;
    private String title;
    private String coverUrl;

    public void setType(BeanType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public DetailPresenter(@NonNull Context context, @NonNull DetailContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);
        sp = context.getSharedPreferences("user_settings", MODE_PRIVATE);
        dbHelper = new DatabaseHelper(context, "History.db", null, 5);
        gson = new Gson();
    }


    @Override
    public void openInBrowser() {
        if (checkNull()) {
            view.showLoadingError();
            return;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            switch (type) {
                case TYPE_ZHIHU:
                    intent.setData(Uri.parse(zhihuDailyStory.getShare_url()));
                    break;
                case TYPE_GUOKR:
                    intent.setData(Uri.parse(Api.GUOKR_ARTICLE_LINK_V1 + id));
                    break;
                case TYPE_DOUBAN:
                    intent.setData(Uri.parse(doubanMomentStory.getShort_url()));
            }

            context.startActivity(intent);

        } catch (android.content.ActivityNotFoundException ex){
            view.showBrowserNotFoundError();
        }
    }

    @Override
    public void shareAsText() {
        if (checkNull()) {
            view.showSharingError();
            return;
        }

        try {
            Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String shareText = "" + title + " ";

            switch (type) {
                case TYPE_ZHIHU:
                    shareText += zhihuDailyStory.getShare_url();
                    break;
                case TYPE_GUOKR:
                    shareText += Api.GUOKR_ARTICLE_LINK_V1 + id;
                    break;
                case TYPE_DOUBAN:
                    shareText += doubanMomentStory.getShort_url();
            }

            shareText = shareText + "\t\t\t" + context.getString(R.string.share_extra);

            shareIntent.putExtra(Intent.EXTRA_TEXT,shareText);
            context.startActivity(Intent.createChooser(shareIntent,context.getString(R.string.share_to)));
        } catch (android.content.ActivityNotFoundException ex){
            view.showLoadingError();
        }

    }

    @Override
    public void openUrl(WebView webView, String url) {
        if (sp.getBoolean("in_app_browser",true)) {
            CustomTabsIntent.Builder customTabsIntent = new CustomTabsIntent.Builder()
                    .setToolbarColor(context.getResources().getColor(R.color.colorAccent))
                    .setShowTitle(true);
            CustomTabActivityHelper.openCustomTab(
                    (Activity) context,
                    customTabsIntent.build(),
                    Uri.parse(url),
                    new CustomFallback() {
                        @Override
                        public void openUri(Activity activity, Uri uri) {
                            super.openUri(activity, uri);
                        }
                    }
            );
        } else {

            try{
                context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            } catch (android.content.ActivityNotFoundException ex){
                view.showBrowserNotFoundError();
            }

        }
    }

    @Override
    public void copyText() {
        if (checkNull()) {
            view.showCopyTextError();
            return;
        }

        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = null;
        switch (type) {
            case TYPE_ZHIHU:
                clipData = ClipData.newPlainText("text", Html.fromHtml(title + "\n" + zhihuDailyStory.getBody()).toString());
                break;
            case TYPE_GUOKR:
                clipData = ClipData.newPlainText("text", Html.fromHtml(guokrStory).toString());
                break;
            case TYPE_DOUBAN:
                clipData = ClipData.newPlainText("text", Html.fromHtml(title + "\n" + doubanMomentStory.getContent()).toString());
        }
        manager.setPrimaryClip(clipData);
        view.showTextCopied();

    }

    @Override
    public void copyLink() {
        if (checkNull()) {
            view.showCopyTextError();
            return;
        }

        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = null;
        switch (type) {
            case TYPE_ZHIHU:
                clipData = ClipData.newPlainText("text", Html.fromHtml(zhihuDailyStory.getShare_url()).toString());
                break;
            case TYPE_GUOKR:
                clipData = ClipData.newPlainText("text", Html.fromHtml(Api.GUOKR_ARTICLE_LINK_V1 + id).toString());
                break;
            case TYPE_DOUBAN:
                clipData = ClipData.newPlainText("text", Html.fromHtml(doubanMomentStory.getOriginal_url()).toString());
        }
        manager.setPrimaryClip(clipData);
        view.showTextCopied();

    }

    @Override
    public void addToOrDeleteFromBookmarks() {
        String tmpTable = "";
        String tmpId = "";
        switch (type) {
            case TYPE_ZHIHU:
                tmpTable = "Zhihu";
                tmpId = "zhihu_id";
                break;
            case TYPE_GUOKR:
                tmpTable = "Guokr";
                tmpId = "guokr_id";
                break;
            case TYPE_DOUBAN:
                tmpTable = "Douban";
                tmpId = "douban_id";
                break;
            default:
                break;
        }

        if (queryIfIsBookmarked()) {
            // delete
            // update Zhihu set bookmark = 0 where zhihu_id = id
            ContentValues values = new ContentValues();
            values.put("bookmark", 0);
            dbHelper.getWritableDatabase().update(tmpTable, values, tmpId + " = ?", new String[]{String.valueOf(id)});
            values.clear();

            view.showDeletedFromBookmarks();
        } else {
            // add
            // update Zhihu set bookmark = 1 where zhihu_id = id

            ContentValues values = new ContentValues();
            values.put("bookmark", 1);
            dbHelper.getWritableDatabase().update(tmpTable, values, tmpId + " = ?", new String[]{String.valueOf(id)});
            values.clear();

            view.showAddedToBookmarks();
        }
    }

    @Override
    public boolean queryIfIsBookmarked() {
        if (id == 0 || type == null) {
            view.showLoadingError();
            return false;
        }

        String tempTable = "";
        String tempId = "";

        switch (type) {
            case TYPE_ZHIHU:
                tempTable = "Zhihu";
                tempId = "zhihu_id";
                break;
            case TYPE_GUOKR:
                tempTable = "Guokr";
                tempId = "guokr_id";
                break;
            case TYPE_DOUBAN:
                tempTable = "Douban";
                tempId = "douban_id";
                break;
            default:
                break;
        }

        String sql = "select * from " + tempTable + " where " + tempId + " = ?";
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                int isBookmarked = cursor.getInt(cursor.getColumnIndex("bookmark"));
                if (isBookmarked == 1) {
                    return true;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        return false;
    }

    @Override
    public void requestData() {
        if (id == 0 || type == null) {
            view.showLoadingError();
            return;
        }

        view.showLoading();
        view.setTitle(title);
        view.showCover(coverUrl);

        // set the web view whether to show the image
        view.setImageMode(sp.getBoolean("no_picture_mode",false));

        switch (type) {
            case TYPE_ZHIHU:
                if (NetworkState.networkConnected(context)) {
                    model.load(Api.ZHIHU_NEWS + id, new OnStringListener() {
                        @Override
                        public void onSuccess(String result) {
                            {
                                Gson gson = new Gson();
                                try {
                                    zhihuDailyStory = gson.fromJson(result, ZhihuDailyStory.class);
                                    if (zhihuDailyStory.getBody() == null) {
                                        view.showResultWithoutBody(zhihuDailyStory.getShare_url());
                                    } else {
                                        view.showResult(convertZhihuContent(zhihuDailyStory.getBody()));
                                    }
                                } catch (JsonSyntaxException e) {
                                    view.showLoadingError();
                                }
                                view.stopLoading();
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {
                            view.stopLoading();
                            view.showLoadingError();
                        }
                    });
                } else {
                    Cursor cursor = dbHelper.getReadableDatabase()
                            .query("Zhihu", null, null, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            if (cursor.getInt(cursor.getColumnIndex("zhihu_id")) == id) {
                                String content = cursor.getString(cursor.getColumnIndex("zhihu_content"));
                                try {
                                    zhihuDailyStory = gson.fromJson(content, ZhihuDailyStory.class);
                                } catch (JsonSyntaxException e) {
                                    view.showResult(content);
                                }
                                view.showResult(convertZhihuContent(zhihuDailyStory.getBody()));
                            }
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                break;

            case TYPE_GUOKR:
                if (NetworkState.networkConnected(context)) {
                    model.load(Api.GUOKR_ARTICLE_LINK_V1 + id, new OnStringListener() {
                        @Override
                        public void onSuccess(String result) {
                            convertGuokrContent(result);
                            view.showResult(guokrStory);
                        }

                        @Override
                        public void onError(VolleyError error) {
                            view.showLoadingError();
                        }
                    });
                } else {
                    Cursor cursor = dbHelper.getReadableDatabase()
                            .query("Guokr", null, null, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            if (cursor.getInt(cursor.getColumnIndex("guokr_id")) == id) {
                                guokrStory = cursor.getString(cursor.getColumnIndex("guokr_content"));
                                convertGuokrContent(guokrStory);
                                view.showResult(guokrStory);
                                break;
                            }
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                break;

            case TYPE_DOUBAN:
                if (NetworkState.networkConnected(context)) {
                    model.load(Api.DOUBAN_ARTICLE_DETAIL + id, new OnStringListener() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                doubanMomentStory = gson.fromJson(result, DoubanMomentStory.class);
                                view.showResult(convertDoubanContent());
                            } catch (JsonSyntaxException e) {
                                view.showLoadingError();
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {
                            view.showLoadingError();
                        }
                    });
                } else {
                    Cursor cursor = dbHelper.getReadableDatabase()
                            .rawQuery("select douban_content from Douban where douban_id = " + id, null);
                    if (cursor.moveToFirst()) {
                        do {
                            if (cursor.getCount() == 1) {
                                doubanMomentStory = gson.fromJson(cursor.getString(0), DoubanMomentStory.class);
                                view.showResult(convertDoubanContent());
                            }
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    break;

                }
            default:
                view.stopLoading();
                view.showLoadingError();
                break;
        }

        view.stopLoading();

    }

    @Override
    public void start() {

    }

    private String convertDoubanContent() {

        if (doubanMomentStory.getContent() == null) {
            return null;
        }
        String css;
        if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_dark.css\" type=\"text/css\">";
        } else {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
        }
        String content = doubanMomentStory.getContent();
        ArrayList<DoubanMomentNews.posts.thumbs> imageList = doubanMomentStory.getPhotos();
        for (int i = 0; i < imageList.size(); i++) {
            String old = "<img id=\"" + imageList.get(i).getTag_name() + "\" />";
            String newStr = "<img id=\"" + imageList.get(i).getTag_name() + "\" "
                    + "src=\"" + imageList.get(i).getMedium().getUrl() + "\"/>";
            content = content.replace(old, newStr);
        }
        StringBuilder builder = new StringBuilder();
        builder.append( "<!DOCTYPE html>\n");
        builder.append("<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        builder.append("<head>\n<meta charset=\"utf-8\" />\n");
        builder.append(css);
        builder.append("\n</head>\n<body>\n");
        builder.append("<div class=\"container bs-docs-container\">\n");
        builder.append("<div class=\"post-container\">\n");
        builder.append(content);
        builder.append("</div>\n</div>\n</body>\n</html>");

        return builder.toString();
    }

    private String convertZhihuContent(String preResult) {

        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES){
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
    }

    private void convertGuokrContent(String content) {
        // 简单粗暴的去掉下载的div部分
        this.guokrStory = content.replace("<div class=\"down\" id=\"down-footer\">\n" +
                "        <img src=\"http://static.guokr.com/apps/handpick/images/c324536d.jingxuan-logo.png\" class=\"jingxuan-img\">\n" +
                "        <p class=\"jingxuan-txt\">\n" +
                "            <span class=\"jingxuan-title\">果壳精选</span>\n" +
                "            <span class=\"jingxuan-label\">早晚给你好看</span>\n" +
                "        </p>\n" +
                "        <a href=\"\" class=\"app-down\" id=\"app-down-footer\">下载</a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"down-pc\" id=\"down-pc\">\n" +
                "        <img src=\"http://static.guokr.com/apps/handpick/images/c324536d.jingxuan-logo.png\" class=\"jingxuan-img\">\n" +
                "        <p class=\"jingxuan-txt\">\n" +
                "            <span class=\"jingxuan-title\">果壳精选</span>\n" +
                "            <span class=\"jingxuan-label\">早晚给你好看</span>\n" +
                "        </p>\n" +
                "        <a href=\"http://www.guokr.com/mobile/\" class=\"app-down\">下载</a>\n" +
                "    </div>", "");

        // 替换css文件为本地文件
        guokrStory = guokrStory.replace("<link rel=\"stylesheet\" href=\"http://static.guokr.com/apps/handpick/styles/d48b771f.article.css\" />",
                "<link rel=\"stylesheet\" href=\"file:///android_asset/guokr.article.css\" />");

        // 替换js文件为本地文件
        guokrStory = guokrStory.replace("<script src=\"http://static.guokr.com/apps/handpick/scripts/9c661fc7.base.js\"></script>",
                "<script src=\"file:///android_asset/guokr.base.js\"></script>");
        if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES){
            guokrStory = guokrStory.replace("<div class=\"article\" id=\"contentMain\">",
                    "<div class=\"article \" id=\"contentMain\" style=\"background-color:#212b30; color:#878787\">");
        }
    }

    private boolean checkNull() {
        return (type == BeanType.TYPE_ZHIHU && zhihuDailyStory == null)
                || (type == BeanType.TYPE_GUOKR && guokrStory == null)
                || (type == BeanType.TYPE_DOUBAN && doubanMomentStory == null);
    }

}
