/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzy.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.ninegrid.NineGridView;
import com.lzy.demo.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/1
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class PicassoImageLoader implements ImageLoader, NineGridView.ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Picasso.with(activity)                                  //配置上下文
                .load(Uri.fromFile(new File(path)))             //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.drawable.ic_default_color)             //设置错误图片
                .placeholder(R.drawable.ic_default_color)       //设置占位图片
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url)//
                .placeholder(R.drawable.ic_default_color)//
                .error(R.drawable.ic_default_color)//
                .into(imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
