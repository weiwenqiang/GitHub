package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTranslationAnimation;
import com.nightonke.wowoviewpager.Enum.Ease;

public class WoWoTranslationAnimationActivity extends WoWoActivity {

    private boolean animationAdded = false;

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_translation_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addAnimations();
    }

    private void addAnimations() {
        if (animationAdded) return;
        animationAdded = true;

        // For translation-animation,
        // do the add-animations job in methods when the views in activity finishing "onLayout".

        View view = findViewById(R.id.test);
        float radius = view.getWidth() / 2;

        ViewAnimation viewAnimation = new ViewAnimation(view);
        viewAnimation.add(WoWoTranslationAnimation.builder().page(0)
                .fromX(view.getTranslationX()).toX(-screenW / 2 + radius)
                .fromY(view.getTranslationY()).toY(-screenH / 2 + radius)
                .ease(ease).build());
        viewAnimation.add(WoWoTranslationAnimation.builder().page(1)
                .fromX(-screenW / 2 + radius).toX(screenW / 2 - radius)
                .fromY(-screenH / 2 + radius).toY(screenH / 2 - radius)
                .ease(ease).build());
        viewAnimation.add(WoWoTranslationAnimation.builder().page(2).start(0).end(0.5)
                .keepX(screenW / 2 - radius)
                .fromY(screenH / 2 - radius).toY(0)
                .ease(Ease.Linear).build());
        viewAnimation.add(WoWoTranslationAnimation.builder().page(2).start(0.5).end(1)
                .fromX(screenW / 2 - radius).toX(-screenW / 2 + radius)
                .keepY(0)
                .ease(ease).build());
        viewAnimation.add(WoWoTranslationAnimation.builder().page(3).start(0).end(0.5)
                .fromX(-screenW / 2 + radius).toX(0)
                .fromY(0).toY(-screenH / 2 + radius)
                .ease(Ease.Linear).build());
        viewAnimation.add(WoWoTranslationAnimation.builder().page(3).start(0.5).end(1)
                .keepX(0)
                .fromY(-screenH / 2 + radius).toY(0)
                .ease(ease).build());

        wowo.addAnimation(viewAnimation);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
