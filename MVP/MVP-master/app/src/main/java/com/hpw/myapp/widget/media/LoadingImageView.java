package com.hpw.myapp.widget.media;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hpw.myapp.R;

/**
 * Created by hpw on 16/12/4.
 */

public class LoadingImageView extends FrameLayout {
    public LoadingImageView(Context context) {
        this(context, null, 0);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView target = (ImageView) findViewById(R.id.target);

        target.setImageResource(R.drawable.loading_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) target.getDrawable();
        animationDrawable.start();
    }
}
