package com.lcodecore.twinklingrefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

/**
 * Created by lcodecore on 2016/10/1.
 */

public class TextHeaderView extends TextView implements IHeaderView {


    public TextHeaderView(Context context) {
        super(context);
    }

    public TextHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) setText("下拉刷新");
        if (fraction > 1f) setText("释放刷新");
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) setText("下拉刷新");
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        setText("正在刷新");
    }

    @Override
    public void onFinish(OnAnimEndListener listener) {
        listener.onAnimEnd();
    }

    @Override
    public void reset() {
        setText("下拉刷新");
    }
}
