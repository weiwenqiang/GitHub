package com.nightonke.wowoviewpager.Enum;

import android.graphics.Color;

/**
 * Created by Weiping Huang at 02:02 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum Chameleon {

    /**
     * Change color in R G B
     */
    RGB,

    /**
     * Change color in H S V
     */
    HSV;

    private static final float ERROR = 0.001f;

    public static float[] toHSVArray(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        float[] array = new float[3];
        double rad = Math.PI * hsv[0] / 180;
        array[0] = (float) Math.cos(rad) * hsv[1];
        array[1] = (float) Math.sin(rad) * hsv[1];
        array[2] = hsv[2];
        return array;
    }

    public static float[][] toHSVArrays(int[] colors) {
        float[][] hsvs = new float[colors.length][];
        for (int i = 0; i < colors.length; i++) hsvs[i] = toHSVArray(colors[i]);
        return hsvs;
    }

    public static int getHSVColor(float[] fromArray, float[] toArray, float offset) {
        float[] array = new float[3];
        array[0] = (toArray[0] - fromArray[0]) * offset + fromArray[0];
        array[1] = (toArray[1] - fromArray[1]) * offset + fromArray[1];
        array[2] = (toArray[2] - fromArray[2]) * offset + fromArray[2];

        float[] hsv = new float[3];
        hsv[1] = (float) Math.sqrt(array[0] * array[0] + array[1] * array[1]);
        hsv[0] = hsv[1] < ERROR ? 0 : (float) (Math.atan2(array[1] / hsv[1], array[0] / hsv[1]) * 180 / Math.PI);
        if (hsv[0] < 0) hsv[0] += 360f;
        hsv[2] = array[2];

        return Color.HSVToColor(hsv);
    }

    public static int[] getHSVColors(float[][] fromArrays, float[][] toArrays, float offset) {
        int[] colors = new int[fromArrays.length];
        for (int i = 0; i < colors.length; i++) colors[i] = getHSVColor(fromArrays[i], toArrays[i], offset);
        return colors;
    }

    public static int[] toARGBArray(int color) {
        return new int[]{Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color)};
    }

    public static int[][] toARGBArrays(int[] colors) {
        int[][] argbs = new int[colors.length][];
        for (int i = 0; i < colors.length; i++) argbs[i] = toARGBArray(colors[i]);
        return argbs;
    }

    public static int getARGBColor(int[] fromArray, int[] toArray, float offset) {
        if (offset < 0) offset = 0;
        if (offset > 1) offset = 1;
        return Color.argb(
                fromArray[0] + (int)((toArray[0] - fromArray[0]) * offset),
                fromArray[1] + (int)((toArray[1] - fromArray[1]) * offset),
                fromArray[2] + (int)((toArray[2] - fromArray[2]) * offset),
                fromArray[3] + (int)((toArray[3] - fromArray[3]) * offset)
        );
    }

    public static int[] getARGBColors(int[][] fromArrays, int[][] toArrays, float offset) {
        int[] colors = new int[fromArrays.length];
        for (int i = 0; i < colors.length; i++) colors[i] = getARGBColor(fromArrays[i], toArrays[i], offset);
        return colors;
    }
}
