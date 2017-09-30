package com.nightonke.wowoviewpager.Animation;

/**
 * Created by Weiping Huang at 19:25 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public interface WoWoAnimationInterface {
    /**
     * Set the view to start-state.
     */
    void toStartState();

    /**
     * Set the view to middle-state.
     *
     * @param offset Offset
     */
    void toMiddleState(float offset);

    /**
     * Set the view to end-state.
     */
    void toEndState();
}
