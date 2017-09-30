package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.hm.horizontalprogressbar.R;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class RoundProgressBar extends HorizonalProgessBar {

    private int mRadius = dp2px(30);
    private int mPaintMaxWidth = 0;
    private RectF outerRectF;
    private boolean mTextVisible = true;
    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mReachHeight = (int) (mUnReachHeight * 2.5f);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        mRadius = (int) ta.getDimension(R.styleable.RoundProgressBar_progressbar_radius, mRadius);
        mTextVisible = ta.getBoolean(R.styleable.RoundProgressBar_progressbar_text_visible,mTextVisible);
        ta.recycle();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaintMaxWidth = Math.max(mReachHeight, mUnReachHeight);
        //默认四个pandding一致
        int except = getPaddingLeft() + getPaddingRight() + mRadius * 2 + mPaintMaxWidth;
        int width = resolveSize(except, widthMeasureSpec);
        int height = resolveSize(except, heightMeasureSpec);
        int result = Math.min(width, height);
        mRadius = (result - getPaddingRight() - getPaddingLeft() - mPaintMaxWidth) / 2;

        outerRectF = new RectF(0, 0, mRadius * 2, mRadius * 2);

        setMeasuredDimension(result, result);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "%";
        mPaint.setColor(mTextColor);
        int textWidth = (int) mPaint.measureText(text);
        int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);
        canvas.save();
        canvas.translate(getPaddingLeft() + mPaintMaxWidth / 2, getPaddingTop() + mPaintMaxWidth / 2);
        //画UnReachBar
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        //画ReachBar

        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);

        //算出弧度
        float sweepAngle = (getProgress() * 1.0f / getMax() * 360);
        canvas.drawArc(outerRectF, -90, sweepAngle, false, mPaint);

        //画出文字
        if (mTextVisible){
            int textX = mRadius - textWidth / 2;
            int textY = mRadius - textHeight;
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mTextColor);
            canvas.drawText(text, textX, textY, mPaint);
        }
        canvas.restore();
    }
}
