package com.hpw.myapp.ui.publish.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.hpw.myapp.ui.publish.DefXhsEmoticons;
import com.hpw.myapp.widget.emoticonskeyboard.interfaces.EmoticonFilter;
import com.hpw.myapp.widget.emoticonskeyboard.utils.EmoticonsKeyboardUtils;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XhsFilter extends EmoticonFilter {

    public static final int WRAP_DRAWABLE = -1;
    private int emoticonSize = -1;
    public static final Pattern XHS_RANGE = Pattern.compile("\\[[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");

    public static Matcher getMatcher(CharSequence matchStr) {
        return XHS_RANGE.matcher(matchStr);
    }

    @Override
    public void filter(EditText editText, CharSequence text, int start, int lengthBefore, int lengthAfter) {
        emoticonSize = emoticonSize == -1 ? EmoticonsKeyboardUtils.getFontHeight(editText) : emoticonSize;
        clearSpan(editText.getText(), start, text.toString().length());
        Matcher m = getMatcher(text.toString().substring(start, text.toString().length()));
        if (m != null) {
            while (m.find()) {
                String key = m.group();
                String icon = DefXhsEmoticons.sXhsEmoticonHashMap.get(key);
                if (!TextUtils.isEmpty(icon)) {
                    emoticonDisplay(editText.getContext(), editText.getText(), icon, emoticonSize, start + m.start(), start + m.end());
                }
            }
        }
    }

    private void clearSpan(Spannable spannable, int start, int end) {
        if (start == end) {
            return;
        }
        EmoticonSpan[] oldSpans = spannable.getSpans(start, end, EmoticonSpan.class);
        for (int i = 0; i < oldSpans.length; i++) {
            spannable.removeSpan(oldSpans[i]);
        }
    }

    public static void emoticonDisplay(Context context, Spannable spannable, String emoticonName, int fontSize, int start, int end) {
        Drawable drawable = getDrawableFromAssets(context, emoticonName);
        if (drawable != null) {
            int itemHeight;
            int itemWidth;
            if (fontSize == WRAP_DRAWABLE) {
                itemHeight = drawable.getIntrinsicHeight();
                itemWidth = drawable.getIntrinsicWidth();
            } else {
                itemHeight = fontSize;
                itemWidth = fontSize;
            }

            drawable.setBounds(0, 0, itemHeight, itemWidth);
            ImageSpan imageSpan = new ImageSpan(drawable);
            spannable.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }
}
