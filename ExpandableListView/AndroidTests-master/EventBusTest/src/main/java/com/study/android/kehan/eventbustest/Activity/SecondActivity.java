package com.study.android.kehan.eventbustest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.study.android.kehan.eventbustest.Bean.EventBusMessage;
import com.study.android.kehan.eventbustest.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kehan on 16-7-25.
 */
public class SecondActivity extends AppCompatActivity {

    private TextView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventBusMessage("我是一个消息"));
//                EventBus.getDefault().post(new String("我是一个消息"));
                finish();
            }
        });
    }
}
