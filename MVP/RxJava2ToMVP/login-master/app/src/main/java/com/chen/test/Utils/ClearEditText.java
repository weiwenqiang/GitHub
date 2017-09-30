package com.chen.test.Utils;

/**
 * Created by long on 17-3-26.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chen.test.R;

public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable rightDrawable;
    private boolean hasFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }


    private void init() {
        // getCompoundDrawables() Returns drawables for the left(0), top(1), right(2) and bottom(3)
        rightDrawable = getCompoundDrawables()[2];// 获取drawableRight
        if (rightDrawable == null) {
            // 如果为空，即没有设置drawableRight，则使用R.mipmap.close这张图片
            rightDrawable = getResources().getDrawable(R.mipmap.qingchu3);
        }
        rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
        // leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        // 默认隐藏图标
        setDrawableVisible(false);
    }

    /**
     * 我们无法直接给EditText设置点击事件，只能通过按下的位置来模拟clear点击事件
     * 当我们按下的位置在图标包括图标到控件右边的间距范围内均算有效
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int start = getWidth() - getTotalPaddingRight() + getPaddingRight(); // 起始位置
                int end = getWidth(); // 结束位置
                boolean available = (event.getX() > start) && (event.getX() < end);
                if (available) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus && getText().length() > 0) {
            setDrawableVisible(true); // 有焦点且有文字时显示图标
        } else {
            setDrawableVisible(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFocus) {
            setDrawableVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public void setDrawableVisible(boolean visible) {
        Drawable right = visible ? rightDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

}