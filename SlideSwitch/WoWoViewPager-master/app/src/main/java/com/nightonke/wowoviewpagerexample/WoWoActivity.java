package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;

public abstract class WoWoActivity extends AppCompatActivity {

    protected WoWoViewPager wowo;

    protected int ease = Ease.Linear;
    protected boolean useSameEaseTypeBack = true;

    protected TextView pageNumber;

    protected abstract int contentViewRes();

    protected int fragmentNumber() {
        return 5;
    }

    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.blue_1,
                R.color.blue_2,
                R.color.blue_3,
                R.color.blue_4,
                R.color.blue_5
        };
    }

    protected int screenW;
    protected int screenH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(contentViewRes());

        init();

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        wowo.setAdapter(WoWoViewPagerAdapter.builder()
                .fragmentManager(getSupportFragmentManager())
                .count(fragmentNumber())                       // Fragment Count
                .colorsRes(fragmentColorsRes())                // Colors of fragments
                .build());
        setPageTV(wowo);

        pageNumber = (TextView) findViewById(R.id.page);

        screenW = WoWoUtil.getScreenWidth(this);
        screenH = WoWoUtil.getScreenHeight(this);
    }

    protected int color(int colorRes) {
        return ContextCompat.getColor(this, colorRes);
    }

    private void setPageTV(WoWoViewPager wowo) {
        wowo.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (pageNumber != null) pageNumber.setText(String.valueOf(position));
            }
        });
    }

    private void init() {
        useSameEaseTypeBack = getIntent().getBooleanExtra("useSameEaseTypeBack", true);
        int easeEnumNumber = getIntent().getIntExtra("easeType", -1);
        switch (easeEnumNumber) {
            case 0: ease = Ease.Linear; break;
            case 1: ease = Ease.InSine; break;
            case 2: ease = Ease.OutSine; break;
            case 3: ease = Ease.InOutSine; break;
            case 4: ease = Ease.InQuad; break;
            case 5: ease = Ease.OutQuad; break;
            case 6: ease = Ease.InOutQuad; break;
            case 7: ease = Ease.InCubic; break;
            case 8: ease = Ease.OutCubic; break;
            case 9: ease = Ease.InOutCubic; break;
            case 10: ease = Ease.InQuart; break;
            case 11: ease = Ease.OutQuart; break;
            case 12: ease = Ease.InOutQuart; break;
            case 13: ease = Ease.InQuint; break;
            case 14: ease = Ease.OutQuint; break;
            case 15: ease = Ease.InOutQuint; break;
            case 16: ease = Ease.InExpo; break;
            case 17: ease = Ease.OutExpo; break;
            case 18: ease = Ease.InOutExpo; break;
            case 19: ease = Ease.InCirc; break;
            case 20: ease = Ease.OutCirc; break;
            case 21: ease = Ease.InOutCirc; break;
            case 22: ease = Ease.InBack; break;
            case 23: ease = Ease.OutBack; break;
            case 24: ease = Ease.InOutBack; break;
            case 25: ease = Ease.InElastic; break;
            case 26: ease = Ease.OutElastic; break;
            case 27: ease = Ease.InOutElastic; break;
            case 28: ease = Ease.InBounce; break;
            case 29: ease = Ease.OutBounce; break;
            case 30: ease = Ease.InOutBounce; break;
        }
    }

    protected int dp2px(float dp) {
        return WoWoUtil.dp2px((int) dp, this);
    }

    protected int dp2px(double dp) {
        return WoWoUtil.dp2px((int) dp, this);
    }
}
