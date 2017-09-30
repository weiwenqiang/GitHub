package com.hpw.mvpframe.utils;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.hpw.mvpframe.R;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String getMoney(float cost) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return "￥" + decimalFormat.format(cost);
    }

    public static class PrimaryClickableSpan extends ClickableSpan {

        private Context context;

        public PrimaryClickableSpan(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View widget) {
            // do nothing
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(context.getResources().getColor(R.color.colorPrimary));
            ds.setUnderlineText(false);
        }
    }

    public static int getChineseCount(CharSequence str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        int count = 0;
        if (!TextUtils.isEmpty(str)) {
            Pattern p = Pattern.compile(regEx);// 计算有几个unicode字
            Matcher m = p.matcher(str);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            count += str.length();
        }
        return count;
    }
}
