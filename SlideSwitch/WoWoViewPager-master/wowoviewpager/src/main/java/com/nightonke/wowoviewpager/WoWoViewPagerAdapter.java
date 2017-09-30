package com.nightonke.wowoviewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Weiping Huang at 15:14 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public class WoWoViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<WoWoViewPagerFragment> fragments;
    private int fragmentsNumber;

    /**
     * fragments' color
     */
    private Integer colorRes = null;
    private Integer color = null;
    private ArrayList<Integer> colorsRes = null;
    private ArrayList<Integer> colors = null;

    public WoWoViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public int getFragmentsNumber() {
        return fragmentsNumber;
    }

    public void setFragmentsNumber(int fragmentsNumber) {
        this.fragmentsNumber = fragmentsNumber;
    }

    @Override
    public Fragment getItem(int position) {
        WoWoViewPagerFragment fragment = null;

        if (position < fragments.size()) fragment = fragments.get(position);

        if (fragment == null) {
            fragment = new WoWoViewPagerFragment();
            if (colorRes != null) {
                // the resource of color of all fragments has been set
                fragment.setColorRes(colorRes);
            } else {
                if (color != null) {
                    // the color of all fragments has been set
                    fragment.setColor(color);
                } else {
                    if (colors != null) {
                        if (position < 0 || position >= colors.size()) {
                            // out of index
                            fragment.setColor(colors.get(position));
                        } else {
                            fragment.setColor(Color.TRANSPARENT);
                        }
                    } else {
                        if (colorsRes != null) {
                            if (position < 0 || position >= colorsRes.size()) {
                                // out of index
                                fragment.setColor(Color.TRANSPARENT);
                            } else {
                                fragment.setColorRes(colorsRes.get(position));
                            }
                        } else {
                            fragment.setColor(Color.TRANSPARENT);
                        }
                    }
                }
            }
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentsNumber;
    }

    public int getColorRes() {
        return colorRes;
    }

    /**
     * set resource of color of fragments
     * @param colorRes resource of color of fragments
     */
    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
        colorsRes = null;
        colors = null;
        color = null;
    }

    public Integer getColor() {
        return color;
    }

    /**
     * set color of fragments
     * @param color color of fragments
     */
    public void setColor(Integer color) {
        this.color = color;
        colorRes = null;
        colors = null;
        colorsRes = null;
    }

    /**
     * set resources of colors of fragments
     * @return resources of colors of fragments
     */
    public ArrayList<Integer> getColorsRes() {
        return colorsRes;
    }

    public void setColorsRes(Integer... colorsRes) {
        setColorsRes(new ArrayList<>(Arrays.asList(colorsRes)));
    }

    /**
     * set resources of colors of fragments
     * @param colorsRes resources of colors of fragments
     */
    public void setColorsRes(ArrayList<Integer> colorsRes) {
        this.colorsRes = colorsRes;
        colors = null;
        color = null;
        colorRes = null;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    /**
     * set colors of fragments
     * @param colors colors
     */
    public void setColors(Integer... colors) {
        setColors(new ArrayList<>(Arrays.asList(colors)));
    }

    /**
     * set colors of fragments
     * @param colors colors
     */
    public void setColors(ArrayList<Integer> colors) {
        this.colors = colors;
        colorRes = null;
        color = null;
        colorsRes = null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FragmentManager fragmentManager;
        private int fragmentNumber = 0;
        private Integer colorRes = null;
        private Integer color = null;
        private ArrayList<Integer> colorsRes = null;
        private ArrayList<Integer> colors = null;

        public Builder fragmentManager(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
            return this;
        }

        public Builder count(int fragmentNumber) {
            this.fragmentNumber = fragmentNumber;
            return this;
        }

        public Builder colorRes(Integer colorRes) {
            this.colorRes = colorRes;
            return this;
        }

        public Builder color(Integer color) {
            this.color = color;
            return this;
        }

        public Builder colorsRes(ArrayList<Integer> colorsRes) {
            this.colorsRes = colorsRes;
            return this;
        }

        public Builder colorsRes(Integer... colorsRes) {
            return colorsRes(new ArrayList<>(Arrays.asList(colorsRes)));
        }

        public Builder colors(ArrayList<Integer> colors) {
            this.colors = colors;
            return this;
        }

        public Builder colors(Integer... colors) {
            return colors(new ArrayList<>(Arrays.asList(colors)));
        }

        public WoWoViewPagerAdapter build() {
            WoWoViewPagerAdapter adapter = new WoWoViewPagerAdapter(fragmentManager);
            adapter.setFragmentsNumber(fragmentNumber);
            if (colorRes != null) adapter.setColorRes(colorRes);
            if (color != null) adapter.setColor(color);
            if (colorsRes != null) adapter.setColorsRes(colorsRes);
            if (colors != null) adapter.setColors(colors);
            return adapter;
        }
    }
}
