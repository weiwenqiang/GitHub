package com.lcodecore.twinklingrefreshlayout.beans;

/**
 * Created by lcodecore on 2016/12/6.
 */

public class Food {
    public String title;
    public String info;
    public int imageSrc;
    public int avatar_id;

    public Food(String title, String info, int imageSrc, int avatar_id) {
        this.title = title;
        this.info = info;
        this.imageSrc = imageSrc;
        this.avatar_id = avatar_id;
    }

}
