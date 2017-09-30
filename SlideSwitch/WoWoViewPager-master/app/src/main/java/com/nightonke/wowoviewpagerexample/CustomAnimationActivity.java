package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Animation.WoWoAnimationInterface;
import com.nightonke.wowoviewpager.Animation.WoWoInterfaceAnimation;

public class CustomAnimationActivity extends WoWoActivity {

    private TextView text;

    @Override
    protected int contentViewRes() {
        return R.layout.activity_custom_animation;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1,
                R.color.blue_1
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = (TextView) findViewById(R.id.test);
        wowo.addAnimation(text)
                .add(CustomAnimation.builder().page(0).from(0).to(10).build())
                .add(CustomAnimation.builder().page(1).from(10).to(20).build())
                .add(CustomAnimation.builder().page(2).from(20).to(0).build())
                .add(CustomAnimation.builder().page(3).from(0).to(30).build());

        // If the animation effect wanted is too simple to create a class,
        // you can use the follow way.
        wowo.addAnimation(text)
                .add(WoWoInterfaceAnimation.builder().page(0).implementedBy(new WoWoAnimationInterface() {
                    @Override
                    public void toStartState() {
                        text.setText("Elevation 0");
                    }

                    @Override
                    public void toMiddleState(float offset) {
                        text.setText("Elevation " + offset);
                    }

                    @Override
                    public void toEndState() {
                        text.setText("Elevation 1");
                    }
                }).build());
    }
}
