package com.chen.test.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by long on 17-3-26.
 */

public class EditTextListener {

    /**
     * 有文字.有焦点(显示蓝色,和清除)
     *
     * @param view
     */
    public static void hasTextAndFocus(EditText view, Context context, int leftBitmap, int rightBitmap) {
        Drawable leftDrawable = ContextCompat.getDrawable(context, leftBitmap);
        Drawable rightDrawable = ContextCompat.getDrawable(context, rightBitmap);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        view.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
    }

    /**
     * 没有文字,有焦点(显示蓝色,不显示清除)
     *
     * @param view
     * @param context
     */
    public static void noTextAndHasFocus(EditText view, Context context, int leftBitmap) {
        Drawable leftDrawable = ContextCompat.getDrawable(context, leftBitmap);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        view.setCompoundDrawables(leftDrawable, null, null, null);
    }

    /**
     * 没有焦点（显示灰色,不显示清除）
     *
     * @param view
     * @param context
     */
    public static void noFocus(EditText view, Context context, int leftBitmap) {
        Drawable leftDrawable = ContextCompat.getDrawable(context, leftBitmap);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        view.setCompoundDrawables(leftDrawable, null, null, null);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
