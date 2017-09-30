package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class StaticActivity extends GuidePageActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wowo.setDraggable(false);
        wowo.setScrollDuration(1000);

        RelativeLayout base = (RelativeLayout) findViewById(R.id.base);

        SeekBar seekBar = new SeekBar(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 20);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        seekBar.setLayoutParams(layoutParams);

        seekBar.setMax(9);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) wowo.setScrollDuration(progress * 500);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(2);
        base.addView(seekBar);

        Button nextButton = new Button(this);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 20, 80);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        nextButton.setLayoutParams(layoutParams);
        nextButton.setText("Next");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wowo.next();
            }
        });
        base.addView(nextButton);

        Button previousButton = new Button(this);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 0, 80);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        previousButton.setLayoutParams(layoutParams);
        previousButton.setText("Previous");
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wowo.previous();
            }
        });
        base.addView(previousButton);
    }
}
