package com.chen.test.Utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chen.test.R;

/**
 * Created by long on 17-3-27.
 */

public class TimeUtils extends CountDownTimer {
    private TextView tv;
    private Context context;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeUtils(long millisInFuture, long countDownInterval, TextView tv, Context context) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv.setText(millisUntilFinished / 1000 + "s后可重新发送");
        tv.setEnabled(false);
        tv.setTextColor(ContextCompat.getColor(context, R.color.blueTextColor));
    }

    @Override
    public void onFinish() {
        tv.setText("发送验证码");
        tv.setEnabled(true);
        tv.setTextColor(ContextCompat.getColor(context, R.color.pressed));
    }
}
