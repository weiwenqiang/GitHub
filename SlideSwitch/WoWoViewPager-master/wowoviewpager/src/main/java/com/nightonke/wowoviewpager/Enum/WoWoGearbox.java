package com.nightonke.wowoviewpager.Enum;

/**
 * Created by Weiping Huang at 10:56 on 2017/4/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class WoWoGearbox implements Gearbox {

    public static final WoWoGearbox Zero = new WoWoGearbox(0);
    public static final WoWoGearbox Positive1 = new WoWoGearbox(1);
    public static final WoWoGearbox Positive2 = new WoWoGearbox(2);
    public static final WoWoGearbox Positive3 = new WoWoGearbox(3);
    public static final WoWoGearbox Positive4 = new WoWoGearbox(4);
    public static final WoWoGearbox Positive5 = new WoWoGearbox(5);  // Default value as ViewPager
    public static final WoWoGearbox Positive6 = new WoWoGearbox(6);
    public static final WoWoGearbox Positive7 = new WoWoGearbox(7);

    public static final WoWoGearbox[] Gearboxes = new WoWoGearbox[]{
            Zero,
            Positive1,
            Positive2,
            Positive3,
            Positive4,
            Positive5,
            Positive6,
            Positive7,
    };

    private int v;

    private WoWoGearbox(int v) {
        this.v = v;
    }

    @Override
    public float getInterpolation(float input) {
        if (v == 0) return input;
        else if (v > 0) return (float) (1 - Math.pow(1 - input, v));
        else return (float) Math.pow(input, -v);
    }
}
