package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.nightonke.wowoviewpager.Enum.Gearbox;
import com.nightonke.wowoviewpager.Enum.WoWoGearbox;

public class GearboxActivity extends GuidePageActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout base = (RelativeLayout) findViewById(R.id.base);

        SeekBar seekBar = new SeekBar(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 20);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        seekBar.setLayoutParams(layoutParams);

        seekBar.setMax(WoWoGearbox.Gearboxes.length - 1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) wowo.setGearbox(WoWoGearbox.Gearboxes[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(3);
        base.addView(seekBar);
    }
}
