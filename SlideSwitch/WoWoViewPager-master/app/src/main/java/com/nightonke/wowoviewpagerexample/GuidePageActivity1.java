package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.WoWoAlphaAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoPathAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoRotationAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoScaleAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoShapeColorAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewTextAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTranslationAnimation;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoPathView;

public class GuidePageActivity1 extends WoWoActivity {

    private int r;
    private boolean animationAdded = false;

    @Override
    protected int contentViewRes() {
        return R.layout.activity_guide_page1;
    }

    @Override
    protected int fragmentNumber() {
        return 4;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.black_background,
                R.color.black_background,
                R.color.black_background,
                R.color.black_background,
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        r = (int)Math.sqrt(screenW * screenW + screenH * screenH) + 10;

        wowo.addTemporarilyInvisibleViews(1, findViewById(R.id.little_cloud), findViewById(R.id.big_cloud));
        wowo.addTemporarilyInvisibleViews(2, findViewById(R.id.sun), findViewById(R.id.nightonke_cloud));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addAnimations();
    }

    private void addAnimations() {
        if (animationAdded) return;
        animationAdded = true;

        addCircleAnimation();
        addNightonkeAnimation();
        addPresentsAnimation();
        addBlueStickAnimation();
        addOrangeStickAnimation();
        addWoWoTextAnimation();
        addViewPagerTextAnimation();
        addMusicStandAnimation();
        addMusicNotesAnimation();
        addBigCloudAnimation();
        addLittleCloudAnimation();
        addPathAnimation();
        addSloganAnimation();
        addSunAnimation();
        addNightonkeCloudAnimation();
        wowo.ready();
    }

    private void addCircleAnimation() {
        View circle = findViewById(R.id.circle);
        wowo.addAnimation(circle)
                .add(WoWoShapeColorAnimation.builder().page(0)
                        .from(color(R.color.gray)).to(color(R.color.light_blue)).build())
                .add(WoWoScaleAnimation.builder().page(0)
                        .fromXY(1).toX(r * 2 / circle.getWidth()).toY(r * 2 / circle.getHeight())
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addNightonkeAnimation() {
        View nightonke = findViewById(R.id.nightonke);
        wowo.addAnimation(nightonke)
                .add(WoWoTranslationAnimation.builder().page(0)
                        .fromX(nightonke.getTranslationX()).toX(-screenW)
                        .keepY(nightonke.getTranslationY())
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addPresentsAnimation() {
        View presents = findViewById(R.id.presents);
        wowo.addAnimation(presents)
                .add(WoWoTranslationAnimation.builder().page(0)
                        .fromX(presents.getTranslationX()).toX(screenW)
                        .keepY(presents.getTranslationY())
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addBlueStickAnimation() {
        View blueStick = findViewById(R.id.blue_stick);
        wowo.addAnimation(blueStick)
                .add(WoWoTranslationAnimation.builder().page(0)
                        .keepX(blueStick.getTranslationX())
                        .fromY(blueStick.getTranslationY()).toY(screenH)
                        .ease(Ease.InCubic).sameEaseBack(false).build());
    }

    private void addOrangeStickAnimation() {
        View orangeStick = findViewById(R.id.orange_stick);
        wowo.addAnimation(orangeStick)
                .add(WoWoTranslationAnimation.builder().page(0)
                        .fromX(orangeStick.getTranslationX()).toX(-screenW)
                        .keepY(orangeStick.getTranslationY())
                        .ease(Ease.InCubic).sameEaseBack(false).build());
    }

    private void addWoWoTextAnimation() {
        View wowoText = findViewById(R.id.wowo);
        wowo.addAnimation(wowoText)
                .add(WoWoRotationAnimation.builder().page(0)
                        .keepX(0).keepY(0)
                        .fromZ(-15).toZ(-150)
                        .ease(Ease.InBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(0)
                        .fromX(wowoText.getTranslationX()).toX(-screenW / 3)
                        .keepY(wowoText.getTranslationY())
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addViewPagerTextAnimation() {
        View viewPagerText = findViewById(R.id.viewpager);
        wowo.addAnimation(viewPagerText)
                .add(WoWoRotationAnimation.builder().page(0)
                        .keepX(0).keepY(0)
                        .fromZ(15).toZ(150)
                        .ease(Ease.InBack).sameEaseBack(false).build())
                .add(WoWoTranslationAnimation.builder().page(0)
                        .fromX(viewPagerText.getTranslationX()).toX(-screenW / 3)
                        .keepY(viewPagerText.getTranslationY())
                        .ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addMusicStandAnimation() {
        View musicStand = findViewById(R.id.music_stand);
        wowo.addAnimation(musicStand)
                .add(WoWoTranslationAnimation.builder().page(0).start(0.3)
                        .fromX(screenW).toX(0).keepY(0).build())
                .add(WoWoTranslationAnimation.builder().page(1).end(0.5)
                        .keepX(0).fromY(0).toY(screenH).build());
    }

    private void addMusicNotesAnimation() {
        View musicNotes = findViewById(R.id.music_notes);
        wowo.addAnimation(musicNotes)
                .add(WoWoTranslationAnimation.builder().page(0).start(0.5)
                        .fromX(screenW).toX(0).keepY(0).build())
                .add(WoWoTranslationAnimation.builder().page(1).end(0.5)
                        .keepX(0).fromY(0).toY(screenH).build());
    }

    private void addBigCloudAnimation() {
        View bigCloud = findViewById(R.id.big_cloud);
        wowo.addAnimation(bigCloud)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .keepX(0)
                        .fromY(-screenH / 2).toY(0).ease(Ease.OutBack).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(0).toX(-screenW)
                        .keepY(0).ease(Ease.InBack).sameEaseBack(false).build());
    }

    private void addLittleCloudAnimation() {
        View littleCloud = findViewById(R.id.little_cloud);
        wowo.addAnimation(littleCloud)
                .add(WoWoTranslationAnimation.builder().page(1)
                        .keepX(0)
                        .fromY(-screenH / 2).toY(0).ease(Ease.OutBack).build())
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(0).toX(-screenW)
                        .keepY(0).ease(Ease.InBack).sameEaseBack(false).build());

    }

    private void addPathAnimation() {
        WoWoPathView pathView = (WoWoPathView) findViewById(R.id.path_view);

        // For different screen size, try to adjust the scale values to see the airplane.
        float xScale = screenW / 720f;
        float yScale = screenH / 1280f;

        pathView.newPath()
                .pathMoveTo(screenW / 2, screenH + yScale * 100)
                .pathCubicTo(xScale * 313, screenH - yScale * 531,
                        xScale * (-234), screenH - yScale * 644,
                        xScale * 141, screenH - yScale * 772)
                .pathCubicTo(xScale * 266, screenH - yScale * 817,
                        xScale * 444, screenH - yScale * 825,
                        xScale * 596, screenH - yScale * 788)
                .pathCubicTo(xScale * 825, screenH - yScale * 727,
                        xScale * 755, screenH - yScale * 592,
                        xScale * (-100), screenH - yScale * 609);
        wowo.addAnimation(pathView)
                .add(WoWoPathAnimation.builder().page(1).from(0).to(1).path(pathView).build())
                .add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
    }

    private void addSloganAnimation() {
        View slogan = findViewById(R.id.slogan);
        slogan.setAlpha(0);
        wowo.addAnimation(slogan)
                .add(WoWoAlphaAnimation.builder().page(1).from(0).to(1).build())
                .add(WoWoTextViewTextAnimation.builder().page(2)
                        .from(getResources().getString(R.string.optimized))
                        .to(getResources().getString(R.string.free)).build());
    }

    private void addSunAnimation() {
        View sun = findViewById(R.id.sun);
        wowo.addAnimation(sun)
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(screenW).toX(0)
                        .fromY(-screenW).toY(0).build())
                .add(WoWoRotationAnimation.builder().page(2)
                        .keepX(0)
                        .keepY(0)
                        .fromZ(0).toZ(360 * 4)
                        .ease(Ease.OutBack).sameEaseBack(false).build());
    }

    private void addNightonkeCloudAnimation() {
        View cloud = findViewById(R.id.nightonke_cloud);
        wowo.addAnimation(cloud)
                .add(WoWoTranslationAnimation.builder().page(2)
                        .fromX(screenW).toX(0)
                        .keepY(0).ease(Ease.OutBack).build());
    }
}
