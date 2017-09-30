package com.hpw.myapp.ui.tv.model;

import java.io.Serializable;

/**
 * Created by hpw on 16/12/4.
 */

public class FirstBannerBean implements Serializable {

    /**
     * content :
     * create_at : 2016-04-29 12:41:37
     * ext : {"type":"play"}
     * fk : 2498193
     * id : 616
     * link :
     * link_object : {"announcement":"","app_shuffling_image":"","avatar":"http://image.quanmin.tv/avatar/a84e775a933a18539b9c0d9f48b2bd28jpg?imageView2/2/w/300/","beauty_cover":"","category_id":"13","category_name":"全民户外","category_slug":"huwai","create_at":"2016-11-25 13:55:42","default_image":"","follow":"46547","grade":"","hidden":false,"intro":"","level":"0","like":"0","love_cover":"","nick":"皂滑丶弄人","play_at":"2016-11-25 13:55:45","recommend_image":"","screen":0,"slug":"","start_time":"2016-11-25 13:55:45","status":"1","thumb":"http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg","title":"韩国 太阳的后裔拍摄地探访","uid":"2498193","video":"http://thumb.quanmin.tv/2498193.mp4?t=1480060800","video_quality":"","view":"19196","weight":"3109460"}
     * priority : 1
     * slot_id : 113
     * status : 1
     * subtitle :
     * thumb : http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg
     * title : 韩国吃喝拿三天两夜又来啦
     */

    private String content;
    private String create_at;
    /**
     * type : play
     */

    private ExtBean ext;
    private String fk;
    private int id;
    private String link;
    /**
     * announcement :
     * app_shuffling_image :
     * avatar : http://image.quanmin.tv/avatar/a84e775a933a18539b9c0d9f48b2bd28jpg?imageView2/2/w/300/
     * beauty_cover :
     * category_id : 13
     * category_name : 全民户外
     * category_slug : huwai
     * create_at : 2016-11-25 13:55:42
     * default_image :
     * follow : 46547
     * grade :
     * hidden : false
     * intro :
     * level : 0
     * like : 0
     * love_cover :
     * nick : 皂滑丶弄人
     * play_at : 2016-11-25 13:55:45
     * recommend_image :
     * screen : 0
     * slug :
     * start_time : 2016-11-25 13:55:45
     * status : 1
     * thumb : http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg
     * title : 韩国 太阳的后裔拍摄地探访
     * uid : 2498193
     * video : http://thumb.quanmin.tv/2498193.mp4?t=1480060800
     * video_quality :
     * view : 19196
     * weight : 3109460
     */

    private OtherBean.DataBean link_object;
    private int priority;
    private int slot_id;
    private int status;
    private String subtitle;
    private String thumb;
    private String title;

    private static class ExtBean {

        public String type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getFk() {
        return fk;
    }

    public void setFk(String fk) {
        this.fk = fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public OtherBean.DataBean getLink_object() {
        return link_object;
    }

    public void setLink_object(OtherBean.DataBean link_object) {
        this.link_object = link_object;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



