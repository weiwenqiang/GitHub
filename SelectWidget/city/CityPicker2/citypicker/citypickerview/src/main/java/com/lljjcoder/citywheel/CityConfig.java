package com.lljjcoder.citywheel;

import android.content.Context;

/**
 * 城市选择器样式配置
 * 作者：liji on 2017/11/4 10:31
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CityConfig {
    
    private Context mContext;
    
    /**
     * Default text color
     */
    public static final String DEFAULT_TEXT_COLOR = "#585858";
    
    /**
     * Default text size
     */
    public static final int DEFAULT_TEXT_SIZE = 18;
    
    private String textColor = DEFAULT_TEXT_COLOR;
    
    private int textSize = DEFAULT_TEXT_SIZE;
    
    /**
     * 滚轮显示的item个数
     */
    private static final int DEF_VISIBLE_ITEMS = 5;
    
    // Count of visible items
    private int visibleItems = DEF_VISIBLE_ITEMS;
    
    /**
     * 省滚轮是否循环滚动
     */
    private boolean isProvinceCyclic = true;
    
    /**
     * 市滚轮是否循环滚动
     */
    private boolean isCityCyclic = true;
    
    /**
     * 区滚轮是否循环滚动
     */
    private boolean isDistrictCyclic = true;
    
    /**
     * item间距
     */
    private int padding = 5;
    
    /**
     * Color.BLACK
     */
    private String cancelTextColorStr = "#000000";
    
    /**
     * Color.BLUE
     */
    private String confirmTextColorStr = "#0000FF";
    
    /**
     * 标题背景颜色
     */
    private String titleBackgroundColorStr = "#E9E9E9";
    
    /**
     * 标题颜色
     */
    private String titleTextColorStr = "#585858";
    
    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private String defaultProvinceName = "江苏";
    
    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private String defaultCityName = "常州";
    
    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private String defaultDistrict = "新北区";
    
    /**
     * 标题
     */
    private String mTitle = "选择地区";
    
    /**
     * 城市数据类别
     * BASE: 基本的城市数据信息，只包含省市区名称，不包含详细的经纬度、code、等数据
     * DETAIL: 包含详细的经纬度、code、省市区数据名称等数据
     */
    public enum CityInfoType {
        BASE, DETAIL
    }
    
    /**
     * 默认显示的城市数据，只包含省市区名称
     */
    private CityInfoType mCityInfoType = CityInfoType.BASE;
    
    public CityInfoType getCityInfoType() {
        return mCityInfoType;
    }
    
    //
    //    /**
    //     * 设置popwindow的背景
    //     */
    //    private int backgroundPop = 0xa0000000;
    //
    /**
     * 定义显示省市区三种滚轮的显示状态
     * PRO:只显示省份的一级选择器
     * PRO_CITY:显示省份和城市二级联动的选择器
     * PRO_CITY_DIS:显示省份和城市和县区三级联动的选择器
     */
    public enum WheelType {
        PRO, PRO_CITY, PRO_CITY_DIS
    }
    
    //    /**
    //     * 是否显示半透明的背景
    //     */
    //    private boolean isShowBackground = false;
    //
    //    /**
    //     * 点击外面的区域是否可以关闭选择，当没有设置半透明背景的条件下，改属性才会起作用
    //     */
    //    private boolean outSideTouchable = true;
    
    /**
     * 定义默认显示省市区三级联动的滚轮选择器
     */
    public WheelType mWheelType = WheelType.PRO_CITY_DIS;
    
    public CityConfig.WheelType getWheelType() {
        return mWheelType;
    }
    
    //    public boolean isShowBackground() {
    //        return isShowBackground;
    //    }
    
    //
    //    public int getBackgroundPop() {
    //        return backgroundPop;
    //    }
    //
    
    //
    //    public void setShowBackground(boolean showBackground) {
    //        isShowBackground = showBackground;
    //    }
    //
    //    public boolean isOutSideTouchable() {
    //        return outSideTouchable;
    //    }
    //
    //    public void setOutSideTouchable(boolean outSideTouchable) {
    //        this.outSideTouchable = outSideTouchable;
    //    }
    
    public Context getContext() {
        return mContext;
    }
    
    public void setContext(Context context) {
        mContext = context;
    }
    
    public String getTextColor() {
        return textColor;
    }
    
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    
    public int getTextSize() {
        return textSize;
    }
    
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
    
    public int getVisibleItems() {
        return visibleItems;
    }
    
    public boolean isProvinceCyclic() {
        return isProvinceCyclic;
    }
    
    public boolean isCityCyclic() {
        return isCityCyclic;
    }
    
    public boolean isDistrictCyclic() {
        return isDistrictCyclic;
    }
    
    public int getPadding() {
        return padding;
    }
    
    public void setPadding(int padding) {
        this.padding = padding;
    }
    
    public String getCancelTextColorStr() {
        return cancelTextColorStr == null ? "" : cancelTextColorStr;
    }
    
    public String getConfirmTextColorStr() {
        return confirmTextColorStr == null ? "" : confirmTextColorStr;
    }
    
    public String getTitleBackgroundColorStr() {
        return titleBackgroundColorStr == null ? "" : titleBackgroundColorStr;
    }
    
    public String getTitleTextColorStr() {
        return titleTextColorStr == null ? "" : titleTextColorStr;
    }
    
    public String getDefaultProvinceName() {
        return defaultProvinceName == null ? "" : defaultProvinceName;
    }
    
    public String getDefaultCityName() {
        return defaultCityName == null ? "" : defaultCityName;
    }
    
    public String getDefaultDistrict() {
        return defaultDistrict == null ? "" : defaultDistrict;
    }
    
    public String getTitle() {
        return mTitle == null ? "" : mTitle;
    }
    
    public void setTitle(String title) {
        mTitle = title;
    }
    
    public CityConfig(Builder builder) {
        
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.visibleItems = builder.visibleItems;
        this.isProvinceCyclic = builder.isProvinceCyclic;
        this.isDistrictCyclic = builder.isDistrictCyclic;
        this.isCityCyclic = builder.isCityCyclic;
        this.mContext = builder.mContext;
        this.padding = builder.padding;
        this.mTitle = builder.mTitle;
        this.titleBackgroundColorStr = builder.titleBackgroundColorStr;
        this.confirmTextColorStr = builder.confirmTextColorStr;
        this.cancelTextColorStr = builder.cancelTextColorStr;
        
        this.defaultDistrict = builder.defaultDistrict;
        this.defaultCityName = builder.defaultCityName;
        this.defaultProvinceName = builder.defaultProvinceName;
        
        this.mWheelType = builder.mWheelType;
        this.mCityInfoType = builder.mCityInfoType;
        this.titleTextColorStr = builder.titleTextColorStr;
        
        //        this.outSideTouchable = builder.outSideTouchable;
        //        this.isShowBackground = builder.isShowBackground;
        //        this.backgroundPop = builder.backgroundPop;
        
    }
    
    public static class Builder {
        /**
         * Default text color
         */
        public static final String DEFAULT_TEXT_COLOR = "#585858";
        
        /**
         * Default text size
         */
        public static final int DEFAULT_TEXT_SIZE = 18;
        
        // Text settings
        private String textColor = DEFAULT_TEXT_COLOR;
        
        private int textSize = DEFAULT_TEXT_SIZE;
        
        /**
         * 滚轮显示的item个数
         */
        private static final int DEF_VISIBLE_ITEMS = 5;
        
        private int visibleItems = DEF_VISIBLE_ITEMS;
        
        /**
         * 省滚轮是否循环滚动
         */
        private boolean isProvinceCyclic = true;
        
        /**
         * 市滚轮是否循环滚动
         */
        private boolean isCityCyclic = true;
        
        /**
         * 区滚轮是否循环滚动
         */
        private boolean isDistrictCyclic = true;
        
        private Context mContext;
        
        /**
         * item间距
         */
        private int padding = 5;
        
        /**
         * Color.BLACK
         */
        private String cancelTextColorStr = "#000000";
        
        /**
         * Color.BLUE
         */
        private String confirmTextColorStr = "#0000FF";
        
        /**
         * 标题背景颜色
         */
        private String titleBackgroundColorStr = "#E9E9E9";
        
        /**
         * 标题颜色
         */
        private String titleTextColorStr = "#585858";
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         */
        private String defaultProvinceName = "江苏";
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         */
        private String defaultCityName = "常州";
        
        /**
         * 第一次默认得显示，一般配合定位，使用
         */
        private String defaultDistrict = "新北区";
        
        /**
         * 标题
         */
        private String mTitle = "选择地区";
        
        /**
         * 定义默认显示省市区三级联动的滚轮选择器
         */
        private CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
        
        /**
         * 城市数据类别
         * BASE: 基本的城市数据信息，只包含省市区名称，不包含详细的经纬度、code、等数据
         * DETAIL: 包含详细的经纬度、code、省市区数据名称等数据
         */
        enum CityInfoType {
            BASE, DETAIL
        }
        
        /**
         * 默认显示的城市数据，只包含省市区名称
         */
        private CityConfig.CityInfoType mCityInfoType = CityConfig.CityInfoType.BASE;
        
        //        
        //        /**
        //         * 是否显示半透明的背景
        //         */
        //        private boolean isShowBackground = false;
        //
        //        /**
        //         * 点击外面的区域是否可以关闭选择，当没有设置半透明背景的条件下，改属性才会起作用
        //         */
        //        private boolean outSideTouchable = true;
        //
        //        /**
        //         * 设置popwindow的背景
        //         */
        //        private int backgroundPop = 0xa0000000;
        
        public Builder(Context context) {
            this.mContext = context;
        }
        
        //        
        //        /**
        //         * 是否显示半透明的背景
        //         * @param isShowBackground
        //         * @return
        //         */
        //        public Builder showBackground(boolean isShowBackground) {
        //            this.isShowBackground = isShowBackground;
        //            return this;
        //        }
        //        
        //        /**
        //         * 点击外面的区域是否可以关闭选择，当没有设置半透明背景的条件下，改属性才会起作用
        //         * @param outSideTouchable
        //         * @return
        //         */
        //        public Builder outSideTouchable(boolean outSideTouchable) {
        //            this.outSideTouchable = outSideTouchable;
        //            return this;
        //        }
        //        
        //        /**
        //         * 设置popwindow的背景
        //         *
        //         * @param backgroundPopColor
        //         * @return
        //         */
        //        public Builder backgroundPop(int backgroundPopColor) {
        //            this.backgroundPop = backgroundPopColor;
        //            return this;
        //        }
        //        
        /**
         * 设置标题背景颜色
         *
         * @param colorBg
         * @return
         */
        public Builder titleBackgroundColor(String colorBg) {
            this.titleBackgroundColorStr = colorBg;
            return this;
        }
        
        /**
         * 设置标题背景颜色
         *
         * @param titleTextColorStr
         * @return
         */
        public Builder titleTextColor(String titleTextColorStr) {
            this.titleTextColorStr = titleTextColorStr;
            return this;
        }
        
        /**
         * 设置标题
         *
         * @param mtitle
         * @return
         */
        public Builder title(String mtitle) {
            this.mTitle = mtitle;
            return this;
        }
        
        public Builder setCityInfoType(CityConfig.CityInfoType infoType) {
            this.mCityInfoType = infoType;
            return this;
        }
        
        /**
         * 显示省市区三级联动的显示状态
         * PRO:只显示省份的一级选择器
         * PRO_CITY:显示省份和城市二级联动的选择器
         * PRO_CITY_DIS:显示省份和城市和县区三级联动的选择器
         * @param wheelType
         * @return
         */
        public Builder setCityWheelType(CityConfig.WheelType wheelType) {
            this.mWheelType = wheelType;
            return this;
        }
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         *
         * @param defaultProvinceName
         * @return
         */
        public Builder province(String defaultProvinceName) {
            this.defaultProvinceName = defaultProvinceName;
            return this;
        }
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         *
         * @param defaultCityName
         * @return
         */
        public Builder city(String defaultCityName) {
            this.defaultCityName = defaultCityName;
            return this;
        }
        
        /**
         * 第一次默认地区显示，一般配合定位，使用
         *
         * @param defaultDistrict
         * @return
         */
        public Builder district(String defaultDistrict) {
            this.defaultDistrict = defaultDistrict;
            return this;
        }
        
        /**
         * 确认按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder confirTextColor(String color) {
            this.confirmTextColorStr = color;
            return this;
        }
        
        /**
         * 取消按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder cancelTextColor(String color) {
            this.cancelTextColorStr = color;
            return this;
        }
        
        /**
         * item文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder textColor(String textColor) {
            this.textColor = textColor;
            return this;
        }
        
        /**
         * item文字大小
         *
         * @param textSize
         * @return
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }
        
        /**
         * 滚轮显示的item个数
         *
         * @param visibleItems
         * @return
         */
        public Builder visibleItemsCount(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }
        
        /**
         * 省滚轮是否循环滚动
         *
         * @param isProvinceCyclic
         * @return
         */
        public Builder provinceCyclic(boolean isProvinceCyclic) {
            this.isProvinceCyclic = isProvinceCyclic;
            return this;
        }
        
        /**
         * 市滚轮是否循环滚动
         *
         * @param isCityCyclic
         * @return
         */
        public Builder cityCyclic(boolean isCityCyclic) {
            this.isCityCyclic = isCityCyclic;
            return this;
        }
        
        /**
         * 区滚轮是否循环滚动
         *
         * @param isDistrictCyclic
         * @return
         */
        public Builder districtCyclic(boolean isDistrictCyclic) {
            this.isDistrictCyclic = isDistrictCyclic;
            return this;
        }
        
        /**
         * item间距
         *
         * @param itemPadding
         * @return
         */
        public Builder itemPadding(int itemPadding) {
            this.padding = itemPadding;
            return this;
        }
        
        public CityConfig build() {
            CityConfig config = new CityConfig(this);
            return config;
        }
    }
}
