package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoLayerListColorAnimation;
import com.nightonke.wowoviewpager.Enum.Chameleon;

public class WoWoLayerListColorAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_layer_list_color_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addAnimations(findViewById(R.id.test1), Chameleon.RGB);
        addAnimations(findViewById(R.id.test2), Chameleon.HSV);
    }

    private void addAnimations(View view, Chameleon chameleon) {
        ViewAnimation animation = new ViewAnimation(view);
        animation.add(WoWoLayerListColorAnimation.builder().page(0)
                .from("#000000", "#ff0000", "#00ff00", "#00ff00", "#ff0000")
                .to("#0000ff", "#00ff00", "#ff0000", "#ff0000", "#00ff00").chameleon(chameleon).build());
        animation.add(WoWoLayerListColorAnimation.builder().page(1)
                .from("#0000ff", "#00ff00", "#ff0000", "#ff0000", "#00ff00")
                .to("#000000", "#ff0000", "#00ff00", "#00ff00", "#ff0000").chameleon(chameleon).build());
        animation.add(WoWoLayerListColorAnimation.builder().page(2)
                .from("#000000", "#ff0000", "#00ff00", "#00ff00", "#ff0000")
                .to("#0000ff", "#00ff00", "#ff0000", "#ff0000", "#00ff00").chameleon(chameleon).build());
        animation.add(WoWoLayerListColorAnimation.builder().page(3).start(0).end(0.5)
                .from("#0000ff", "#00ff00", "#ff0000", "#ff0000", "#00ff00")
                .to("#ffff00", "#0000ff", "#000000", "#ff00ff", "#0000ff").chameleon(chameleon).build());
        animation.add(WoWoLayerListColorAnimation.builder().page(3).start(0.5).end(1)
                .from("#ffff00", "#0000ff", "#000000", "#ff00ff", "#0000ff")
                .to("#ff00ff", "#000000", "#0000ff", "#ffff00", "#000000").chameleon(chameleon).build());

        wowo.addAnimation(animation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
