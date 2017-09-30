package com.hm.horizontalprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import view.HorizonalProgessBar;
import view.RoundProgressBar;

public class MainActivity extends AppCompatActivity {

    private HorizonalProgessBar horizonalProgessBar;
    private static final int MSG_PROGRESS = 0x001;
    private RoundProgressBar roundProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizonalProgessBar = (HorizonalProgessBar) findViewById(R.id.id_horizonalbar);
        roundProgressBar = (RoundProgressBar) findViewById(R.id.id_roundbar);
        mHandler.sendEmptyMessage(MSG_PROGRESS);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currenProgress = horizonalProgessBar.getProgress();
            horizonalProgessBar.setProgress(++currenProgress);
            roundProgressBar.setProgress(++currenProgress);
            if (currenProgress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS,100);
        }
    };
}
