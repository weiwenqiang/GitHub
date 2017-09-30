package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.hm.horizontalprogressbar.R;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class HorizonalProgessBar extends ProgressBar {

    /**
     * 设置默认的控件属性
     */

    /**
     * 文字默认的大小
     */
    private static final int DEFAULT_TEXT_SIZE = 10;
    /**
     * 文字的默认间距
     */
    private static final int DEFAULT_TEXT_OFFSET = 10;
    /**
     * 文字的默认颜色
     */
    private static final int DEFAULT_TEXT_COLOR = 0xFFD3D6DA;

    /**
     * unReach的默认颜色
     */
    private static final int DEFAULT_UNREACH_COLOR = 0xFF00FF00;
    /**
     * unReach的默认高度
     */
    private static final int DEFAULT_UNREACH_HEIGHT = 2;

    /**
     * Reach的默认颜色
     */
    private static final int DEFAULT_REACH_COLOR = 0xFF895412;
    /**
     * Reach的默认高度
     */
    private static final int DEFAULT_REACH_HEIGHT = 2;

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mTextOffSet = dp2px(DEFAULT_TEXT_OFFSET);
    protected int mReachColor = DEFAULT_REACH_COLOR;
    protected int mReachHeight = dp2px(DEFAULT_REACH_HEIGHT);
    protected int mUnReachColor = DEFAULT_UNREACH_COLOR;
    protected int mUnReachHeight = dp2px(DEFAULT_UNREACH_HEIGHT);

    protected int mRealWidth = 0;
    protected Paint mPaint = new Paint();

    public HorizonalProgessBar(Context context) {
        this(context, null);
    }

    public HorizonalProgessBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizonalProgessBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
        mRealWidth = getMeasuredWidth() - getPaddingRight() - getPaddingLeft();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        float radio = getProgress() * 1.0f / getMax();
        float progressX = radio * mRealWidth;
        boolean unNeedDrawUnReach = false;
        String textStr = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(textStr);
        if (progressX + textWidth > mRealWidth) {
            progressX = mRealWidth - textWidth;
            unNeedDrawUnReach = true;
        }
        float endX = progressX - mTextOffSet / 2;
        //画第一条线
        if (endX > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }
        //画文字
        mPaint.setColor(mTextColor);
        int textY = (int) (-(mPaint.ascent() + mPaint.descent()) / 2);
        canvas.drawText(textStr, progressX, textY, mPaint);

        //画第二条线
        if (!unNeedDrawUnReach){
            int startX = (int) (progressX + textWidth + mTextOffSet/2);
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(startX,0,mRealWidth,0,mPaint);
        }
        canvas.restore();
    }

    private int measureHeight(int heightMeasureSpec) {
        int resultSize = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            resultSize = heightSize;
        } else {
            int textHeight = (int) (mPaint.ascent() - mPaint.descent());
            resultSize = getPaddingBottom() + getPaddingTop() + Math.max(Math.max(mUnReachHeight, mReachHeight), Math.abs(textHeight));
            if (heightMode == MeasureSpec.AT_MOST) {
                resultSize = Math.min(heightSize, resultSize);
            }
        }
        return resultSize;
    }

    protected int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    protected int sp2px(int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HorizonalProgessBar);
        mTextSize = (int) ta.getDimension(R.styleable.HorizonalProgessBar_progressbar_text_size, mTextSize);
        mTextColor = ta.getColor(R.styleable.HorizonalProgessBar_progressbar_text_color, mTextColor);
        mTextOffSet = (int) ta.getDimension(R.styleable.HorizonalProgessBar_progressbar_text_offset, mTextOffSet);
        mReachColor = ta.getColor(R.styleable.HorizonalProgessBar_progressbar_reach_color, mReachColor);
        mReachHeight = (int) ta.getDimension(R.styleable.HorizonalProgessBar_progressbar_reach_height, mReachHeight);
        mUnReachColor = ta.getColor(R.styleable.HorizonalProgessBar_progressbar_unreach_color, mUnReachColor);
        mUnReachHeight = (int) ta.getDimension(R.styleable.HorizonalProgessBar_progressbar_unreach_height, mUnReachHeight);
        ta.recycle();
    }
}
