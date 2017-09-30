package com.study.android.kehan.eventbustest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.android.kehan.eventbustest.Bean.EventBusMessage;
import com.study.android.kehan.eventbustest.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by kehan on 16-7-25.
 */
public class FirstActivity extends AppCompatActivity {

    private TextView start;
    private TextView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);  //  在需要接收消息的页面注册EventBus
        setContentView(R.layout.first_layout);
        start = (TextView) findViewById(R.id.start_activity);
        show = (TextView) findViewById(R.id.show);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);  //  在注册了EventBus页面要被销毁时注销EventBus
    }

    @Subscribe
    public void onEventMainThread(EventBusMessage msg) {
        Toast.makeText(FirstActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
        show.setText(msg.getMessage());
        Log.i("FirstActivity", "onEventMainThread" + "被调用了");
    }

    @Subscribe
    public void onEvent(String msg) {
        Log.i("FirstActivity", "onEvent" + "被调用了");
    }

    @Subscribe
    public void onEventBackground(EventBusMessage msg) {
        Log.i("FirstActivity", "onEventBackground" + "被调用了");
    }

    @Subscribe
    public void onEventAsync(EventBusMessage msg) {
        Log.i("FirstActivity", "onEventAsync" + "被调用了");
    }
}
