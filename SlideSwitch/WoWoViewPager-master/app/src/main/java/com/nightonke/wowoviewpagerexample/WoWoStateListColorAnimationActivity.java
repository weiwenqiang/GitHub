package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoStateListColorAnimation;
import com.nightonke.wowoviewpager.Enum.Chameleon;

public class WoWoStateListColorAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_state_list_color_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addAnimations(findViewById(R.id.test1), Chameleon.RGB);
        addAnimations(findViewById(R.id.test2), Chameleon.HSV);
    }

    private void addAnimations(View view, Chameleon chameleon) {
        ViewAnimation viewAnimation = new ViewAnimation(view);
        viewAnimation.add(WoWoStateListColorAnimation.builder().page(0)
                .from("#ff0000", "#ffff00").to("#ffff00", "#00ff00").chameleon(chameleon).build());
        viewAnimation.add(WoWoStateListColorAnimation.builder().page(1)
                .from("#ffff00", "#00ff00").to("#000000", "#0000ff").chameleon(chameleon).build());
        viewAnimation.add(WoWoStateListColorAnimation.builder().page(2)
                .from("#000000", "#0000ff").to("#ff0000", "#ffff00").chameleon(chameleon).build());
        viewAnimation.add(WoWoStateListColorAnimation.builder().page(3).start(0).end(0.5)
                .from("#ff0000", "#ffff00").to("#000000", "#0000ff").chameleon(chameleon).build());
        viewAnimation.add(WoWoStateListColorAnimation.builder().page(3).start(0.5).end(1)
                .from("#000000", "#0000ff").to("#ff0000", "#ffff00").chameleon(chameleon).build());

        wowo.addAnimation(viewAnimation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
