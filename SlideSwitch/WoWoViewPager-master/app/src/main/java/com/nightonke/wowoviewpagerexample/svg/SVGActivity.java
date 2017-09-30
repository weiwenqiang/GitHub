package com.nightonke.wowoviewpagerexample.svg;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.WoWoAlphaAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoInterfaceAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTranslationAnimation;
import com.nightonke.wowoviewpager.svg.WoWoSvgView;
import com.nightonke.wowoviewpagerexample.R;
import com.nightonke.wowoviewpagerexample.WoWoActivity;

public class SVGActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_svg;
    }

    @Override
    protected int fragmentNumber() {
        return 6;
    }

    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.blue_1,
                R.color.blue_2,
                R.color.blue_3,
                R.color.blue_4,
                R.color.blue_5,
                R.color.blue_5,
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WoWoSvgView svgView0 = (WoWoSvgView) findViewById(R.id.svg_0);
        WoWoSvgView svgView1 = (WoWoSvgView) findViewById(R.id.svg_1);
        WoWoSvgView svgView2 = (WoWoSvgView) findViewById(R.id.svg_2);
        WoWoSvgView svgView3 = (WoWoSvgView) findViewById(R.id.svg_3);
        WoWoSvgView svgView4 = (WoWoSvgView) findViewById(R.id.svg_4);

        setSvg(svgView0, SVG.GOOGLE);
        setSvg(svgView1, SVG.GITHUB);
        setSvg(svgView2, SVG.TWITTER);
        setSvg(svgView3, SVG.JRUMMY_APPS);
        setSvg(svgView4, SVG.BUSYBOX_LOGO);

        wowo.addAnimation(svgView0)
                .add(WoWoInterfaceAnimation.builder().page(0).implementedBy(svgView0).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(0).build())
                .add(WoWoAlphaAnimation.builder().page(1).from(1).to(0).build());
        wowo.addAnimation(svgView1)
                .add(WoWoInterfaceAnimation.builder().page(1).implementedBy(svgView1).build())
                .add(WoWoTranslationAnimation.builder().page(2).fromX(0).toX(-screenW).keepY(0).build())
                .add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
        wowo.addAnimation(svgView2)
                .add(WoWoInterfaceAnimation.builder().page(2).implementedBy(svgView2).build())
                .add(WoWoTranslationAnimation.builder().page(3).fromX(0).toX(-screenW).keepY(0).build())
                .add(WoWoAlphaAnimation.builder().page(3).from(1).to(0).build());
        wowo.addAnimation(svgView3)
                .add(WoWoInterfaceAnimation.builder().page(3).implementedBy(svgView3).build())
                .add(WoWoTranslationAnimation.builder().page(4).fromX(0).toX(-screenW).keepY(0).build())
                .add(WoWoAlphaAnimation.builder().page(4).from(1).to(0).build());
        wowo.addAnimation(svgView4)
                .add(WoWoInterfaceAnimation.builder().page(4).implementedBy(svgView4).build());

        wowo.ready();
    }

    private void setSvg(WoWoSvgView svgView, SVG svg) {
        svgView.setGlyphStrings(svg.glyphs);
        svgView.setFillColors(svg.colors);
        svgView.setViewportSize(svg.width, svg.height);
        svgView.setTraceResidueColor(0x32000000);
        svgView.setTraceColors(svg.colors);
        svgView.setTraceTimePerGlyph(5000);
        svgView.setTraceTime(5000);
        svgView.setFillTime(5000);
        svgView.setFillStart(8000);
        svgView.rebuildGlyphData();
    }
}
