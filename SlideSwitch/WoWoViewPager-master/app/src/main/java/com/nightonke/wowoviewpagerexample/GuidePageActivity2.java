package com.nightonke.wowoviewpagerexample;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.nightonke.wowoviewpager.Animation.WoWoAlphaAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoElevationAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoPathAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoRotationAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoScaleAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoShapeColorAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewColorAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewTextAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTranslationAnimation;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoPathView;

public class GuidePageActivity2 extends WoWoActivity {

    private int r;
    private boolean animationAdded = false;
    private ImageView targetPlanet;
    private View loginLayout;

    @Override
    protected int contentViewRes() {
        return R.layout.activity_guide_page2;
    }

    @Override
    protected int fragmentNumber() {
        return 4;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.white,
                R.color.white,
                R.color.white,
                R.color.white,
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        r = (int)Math.sqrt(screenW * screenW + screenH * screenH) + 10;

        ImageView earth = (ImageView) findViewById(R.id.earth);
        targetPlanet = (ImageView) findViewById(R.id.planet_target);
        loginLayout = findViewById(R.id.login_layout);

        earth.setY(screenH / 2);
        targetPlanet.setY(-screenH / 2 - screenW / 2);
        targetPlanet.setScaleX(0.25f);
        targetPlanet.setScaleY(0.25f);

        wowo.addTemporarilyInvisibleViews(0, earth, findViewById(R.id.cloud_blue), findViewById(R.id.cloud_red));
        wowo.addTemporarilyInvisibleViews(0, targetPlanet);
        wowo.addTemporarilyInvisibleViews(2, loginLayout, findViewById(R.id.button));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addAnimations();
    }

    private void addAnimations() {
        if (animationAdded) return;
        animationAdded = true;

        addEarthAnimation();
        addCloudAnimation();
        addTextAnimation();
        addRocketAnimation();
        addCircleAnimation();
        addMeteorAnimation();
        addPlanetAnimation();
        addPlanetTargetAnimation();
        addLoginLayoutAnimation();
        addButtonAnimation();
        addEditTextAnimation();

        wowo.ready();

        // Do this the prevent the edit-text and button views on login layout
        // to intercept the drag event.
        wowo.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                loginLayout.setEnabled(position == 3);
                loginLayout.setVisibility(position + positionOffset <= 2 ? View.INVISIBLE : View.VISIBLE);
            }
        });
    }

    private void addEarthAnimation() {
        View earth = findViewById(R.id.earth);
        wowo.addAnimation(earth)
                .add(WoWoRotationAnimation.builder().page(0).keepX(0).keepY(0).fromZ(0).toZ(180).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(1).keepX(0).keepY(0).fromZ(180).toZ(720).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(2).keepX(0).keepY(0).fromZ(720).toZ(1260).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(0.5).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(2).fromXY(0.5).toXY(0.25).ease(Ease.OutBack).build());
    }

    private void addCloudAnimation() {
        wowo.addAnimation(findViewById(R.id.cloud_blue))
                .add(WoWoTranslationAnimation.builder().page(0).fromX(screenW).toX(0).keepY(0).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(screenW).keepY(0).ease(Ease.InBack).sameEaseBack(false).build());

        wowo.addAnimation(findViewById(R.id.cloud_red))
                .add(WoWoTranslationAnimation.builder().page(0).fromX(-screenW).toX(0).keepY(0).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(0).ease(Ease.InBack).sameEaseBack(false).build());

        wowo.addAnimation(findViewById(R.id.cloud_yellow))
                .add(WoWoTranslationAnimation.builder().page(0).keepX(0).fromY(0).toY(dp2px(50)).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(dp2px(50)).ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addTextAnimation() {
        View text = findViewById(R.id.text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) text.setZ(50);
        String[] texts = new String[]{
                "HOME?",
                "OR SKY?",
                "OR UNIVERSE?",
                "Let's Discover More!",
        };
        wowo.addAnimation(text)
                .add(WoWoTextViewTextAnimation.builder().page(0).from(texts[0]).to(texts[1]).build())
                .add(WoWoTextViewTextAnimation.builder().page(1).from(texts[1]).to(texts[2]).build())
                .add(WoWoTextViewTextAnimation.builder().page(2).from(texts[2]).to(texts[3]).build())
                .add(WoWoTextViewColorAnimation.builder().page(1).from("#05502f").to(Color.WHITE).build());
    }

    private void addRocketAnimation() {
        WoWoPathView pathView = (WoWoPathView) findViewById(R.id.path_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) pathView.setZ(50);

        // For different screen size, try to adjust the scale values to see the airplane.
        float xScale = 1;
        float yScale = 1;

        pathView.newPath()
                .pathMoveTo(xScale * (-100), screenH - 100)
                .pathCubicTo(screenW / 2, screenH - 100,
                        screenW / 2, screenH - 100,
                        screenW / 2, yScale * (-100));
        wowo.addAnimation(pathView)
                .add(WoWoPathAnimation.builder().page(0).from(0).to(0.50).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(1).from(0.50).to(0.75).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(2).from(0.75).to(1).path(pathView).build())
                .add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
    }

    private void addCircleAnimation() {
        View circle = findViewById(R.id.circle);
        wowo.addAnimation(circle)
                .add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(r * 2 / circle.getWidth()).build())
                .add(WoWoShapeColorAnimation.builder().page(1).from("#f9dc0a").to("#05502f").build());
    }

    private void addMeteorAnimation() {
        View meteor = findViewById(R.id.meteor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) meteor.setZ(50);
        float fullOffset = screenW + meteor.getWidth();
        float offset = fullOffset / 2;
        wowo.addAnimation(meteor)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .fromX(0).fromY(0)
                        .toX(offset).toY(offset).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(offset).fromY(offset)
                        .toX(fullOffset).toY(fullOffset).ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addPlanetAnimation() {
        View planet0 = findViewById(R.id.planet_0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) planet0.setZ(50);
        wowo.addAnimation(planet0)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .keepX(0)
                        .fromY(0).toY(planet0.getHeight() + 200)
                        .ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(0).toX(screenW)
                        .keepY(planet0.getHeight() + 200)
                        .ease(Ease.InBack).sameEaseBack(false).build());

        View planet1 = findViewById(R.id.planet_1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) planet1.setZ(50);
        wowo.addAnimation(planet1)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .fromX(0).toX(-planet1.getWidth())
                        .keepY(0)
                        .ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(-planet1.getWidth()).toX(-screenW - planet1.getWidth())
                        .keepY(0)
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addPlanetTargetAnimation() {
        wowo.addAnimation(targetPlanet)
                .add(WoWoRotationAnimation.builder().page(1).keepX(0).keepY(0).fromZ(0).toZ(180).ease(Ease.OutBack).build())
                .add(WoWoRotationAnimation.builder().page(2).keepX(0).keepY(0).fromZ(180).toZ(360).ease(Ease.OutBack).build())
                .add(WoWoTranslationAnimation.builder().page(0).keepX(0)
                        .fromY(-screenH / 2 - screenW / 2)
                        .toY(-screenH / 2).ease(Ease.OutBack).sameEaseBack(false).build())
                .add(WoWoScaleAnimation.builder().page(1).fromXY(0.25).toXY(0.5).ease(Ease.OutBack).build())
                .add(WoWoScaleAnimation.builder().page(2).fromXY(0.5).toXY(1).ease(Ease.OutBack).build());
    }

    private void addLoginLayoutAnimation() {
        View layout = findViewById(R.id.login_layout);
        wowo.addAnimation(layout)
                .add(WoWoAlphaAnimation.builder().page(1).start(1).end(1).from(0).to(1).build())
                .add(WoWoShapeColorAnimation.builder().page(2).from("#05502f").to("#0aa05f").build())
                .add(WoWoElevationAnimation.builder().page(2).from(0).to(40).build());
    }

    private void addButtonAnimation() {
        View button = findViewById(R.id.button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) button.setZ(50);
        wowo.addAnimation(button)
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
    }

    private void addEditTextAnimation() {
        wowo.addAnimation(findViewById(R.id.username))
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
        wowo.addAnimation(findViewById(R.id.password))
                .add(WoWoAlphaAnimation.builder().page(2).from(0).to(1).build());
    }
}
