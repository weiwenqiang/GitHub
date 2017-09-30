package com.nightonke.wowoviewpager.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.WoWoAnimationInterface;
import com.nightonke.wowoviewpager.R;


/**
 * Created by Weiping Huang at 11:24 on 2017/4/1
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Modified from https://github.com/Cutta/GifView
 */

public class WoWoGifView extends View implements WoWoAnimationInterface {

    private static final int DEFAULT_MOVIE_VIEW_DURATION = 1000;

    private int mMovieResourceId;
    private Movie movie;

    /**
     * Position for drawing animation frames in the center of the view.
     */
    private float mLeft;
    private float mTop;

    /**
     * Scaling factor to fit the animation within view bounds.
     */
    private float mScale;

    /**
     * Scaled movie frames width and height.
     */
    private int mMeasuredMovieWidth;
    private int mMeasuredMovieHeight;

    private float mProcess = 0;
    private float mDuration = 0;

    public WoWoGifView(Context context) {
        super(context);
        init(context, null);
    }

    public WoWoGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WoWoGifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Starting from HONEYCOMB(Api Level:11) have to turn off HW acceleration to draw movie on Canvas.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WoWoGifView);

        mMovieResourceId = array.getResourceId(R.styleable.WoWoGifView_wowo_src, getResources().getInteger(R.integer.default_gifResource));

        array.recycle();

        if (mMovieResourceId != 0) setGifResource(mMovieResourceId);
    }

    public void setGifResource(int movieResourceId) {
        this.mMovieResourceId = movieResourceId;
        movie = Movie.decodeStream(getResources().openRawResource(mMovieResourceId));
        mDuration = movie.duration();
        if (mDuration == 0) mDuration = DEFAULT_MOVIE_VIEW_DURATION;
        requestLayout();
    }

    public int getGifResource() { return this.mMovieResourceId; }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (movie != null) {
            int movieWidth = movie.width();
            int movieHeight = movie.height();

			/*
             * Calculate horizontal scaling
			 */
            float scaleH = 1f;
            int measureModeWidth = MeasureSpec.getMode(widthMeasureSpec);

            if (measureModeWidth != MeasureSpec.UNSPECIFIED) {
                int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
                if (movieWidth > maximumWidth) {
                    scaleH = (float) movieWidth / (float) maximumWidth;
                }
            }

			/*
             * calculate vertical scaling
			 */
            float scaleW = 1f;
            int measureModeHeight = MeasureSpec.getMode(heightMeasureSpec);

            if (measureModeHeight != MeasureSpec.UNSPECIFIED) {
                int maximumHeight = MeasureSpec.getSize(heightMeasureSpec);
                if (movieHeight > maximumHeight) {
                    scaleW = (float) movieHeight / (float) maximumHeight;
                }
            }

			/*
             * calculate overall scale
			 */
            mScale = 1f / Math.max(scaleH, scaleW);

            mMeasuredMovieWidth = (int) (movieWidth * mScale);
            mMeasuredMovieHeight = (int) (movieHeight * mScale);

            setMeasuredDimension(mMeasuredMovieWidth, mMeasuredMovieHeight);

        } else {
			/*
			 * No movie set, just set minimum available size.
			 */
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
		/*
		 * Calculate mLeft / mTop for drawing in center
		 */
        mLeft = (getWidth() - mMeasuredMovieWidth) / 2f;
        mTop = (getHeight() - mMeasuredMovieHeight) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (movie != null) drawMovieFrame(canvas);
    }

    /**
     * Draw current GIF frame
     */
    private void drawMovieFrame(Canvas canvas) {
        movie.setTime((int) (mProcess * mDuration));
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(mScale, mScale);
        movie.draw(canvas, mLeft / mScale, mTop / mScale);
        canvas.restore();
    }

    private void setProcess(float process) {
        this.mProcess = process;
        invalidate();
    }

    @Override
    public void toStartState() {
        setProcess(0);
    }

    @Override
    public void toMiddleState(float offset) {
        if (offset < 0) offset = 0;
        if (offset > 1) offset = 1;
        setProcess(offset);
    }

    @Override
    public void toEndState() {
        setProcess(1);
    }
}
