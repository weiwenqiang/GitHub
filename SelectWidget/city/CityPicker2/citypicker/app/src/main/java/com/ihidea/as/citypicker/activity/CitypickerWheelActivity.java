package com.ihidea.as.citypicker.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;

public class CitypickerWheelActivity extends AppCompatActivity {
    
    EditText mTitleEt;
    
    EditText mTitleColorEt;
    
    EditText mTitleBgEt;
    
    EditText mItemTextSizeEt;
    
    EditText mItemTextColorEt;
    
    EditText mProEt;
    
    EditText mCityEt;
    
    EditText mAreaEt;
    
    EditText mConfirmTextColorEt;
    
    EditText mCancelTextColorEt;
    
    EditText mProVisibleCountEt;
    
    EditText mItemPaddingEt;
    
    CheckBox mProCyclicCk;
    
    CheckBox mCityCyclicCk;
    
    CheckBox mAreaCyclicCk;
    
    TextView mResetSettingTv;
    
    TextView mSubmitTv;
    
    TextView mResultTv;
    
    TextView mBaseTv;
    
    TextView mDetailTv;
    
    TextView mOneTv;
    
    TextView mTwoTv;
    
    TextView mThreeTv;
    
    private String textColor = "0xFF585858";
    
    private int textSize = 18;
    
    private int visibleItems = 5;
    
    private boolean isProvinceCyclic = true;
    
    private boolean isCityCyclic = true;
    
    private boolean isDistrictCyclic = true;
    
    private int padding = 5;
    
    private String cancelTextColorStr = "#000000";
    
    private String confirmTextColorStr = "#0000FF";
    
    private String titleBackgroundColorStr = "#E9E9E9";
    
    private String titleTextColorStr = "#585858";
    
    private String defaultProvinceName = "江苏";
    
    private String defaultCityName = "常州";
    
    private String defaultDistrict = "新北区";
    
    private String mTitle = "选择地区";
    
    private CityConfig.CityInfoType mCityInfoType = CityConfig.CityInfoType.BASE;
    
    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_wheel);
        findView();
        
    }
    
    private void findView() {
        
        mResultTv = (TextView) findViewById(R.id.result_tv);
        mTitleEt = (EditText) findViewById(R.id.title_et);
        mTitleColorEt = (EditText) findViewById(R.id.title_text_color_et);
        mTitleBgEt = (EditText) findViewById(R.id.title_bg_et);
        mItemTextSizeEt = (EditText) findViewById(R.id.item_text_size_et);
        mItemTextColorEt = (EditText) findViewById(R.id.item_text_color_et);
        mProEt = (EditText) findViewById(R.id.pro_et);
        mCityEt = (EditText) findViewById(R.id.city_et);
        mAreaEt = (EditText) findViewById(R.id.area_et);
        mConfirmTextColorEt = (EditText) findViewById(R.id.confirm_text_color_et);
        mCancelTextColorEt = (EditText) findViewById(R.id.cancel_text_color_et);
        mProVisibleCountEt = (EditText) findViewById(R.id.pro_visible_count_et);
        mItemPaddingEt = (EditText) findViewById(R.id.item_padding_et);
        mProCyclicCk = (CheckBox) findViewById(R.id.pro_cyclic_ck);
        mCityCyclicCk = (CheckBox) findViewById(R.id.city_cyclic_ck);
        mAreaCyclicCk = (CheckBox) findViewById(R.id.area_cyclic_ck);
        mResetSettingTv = (TextView) findViewById(R.id.reset_setting_tv);
        mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        mBaseTv = (TextView) findViewById(R.id.base_tv);
        mDetailTv = (TextView) findViewById(R.id.detail_tv);
        mOneTv = (TextView) findViewById(R.id.one_tv);
        mTwoTv = (TextView) findViewById(R.id.two_tv);
        mThreeTv = (TextView) findViewById(R.id.three_tv);
        
        //提交
        mSubmitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheel();
            }
        });
        
        //重置属性
        mResetSettingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        
        //一级联动，只显示省份，不显示市和区
        mOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO;
                setWheelType(mWheelType);
            }
        });
        
        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY;
                setWheelType(mWheelType);
            }
        });
        
        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
                setWheelType(mWheelType);
            }
        });
        
        //显示基础信息
        mBaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCityInfoType = CityConfig.CityInfoType.BASE;
                setCityInfoType(mCityInfoType);
            }
        });
        
        //显示详细的城市数据信息
        mDetailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCityInfoType = CityConfig.CityInfoType.DETAIL;
                setCityInfoType(mCityInfoType);
                
            }
        });
        
        //省份是否循环显示
        mProCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isProvinceCyclic = isChecked;
            }
        });
        
        //市是否循环显示
        mCityCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCityCyclic = isChecked;
            }
        });
        
        //区是否循环显示
        mAreaCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDistrictCyclic = isChecked;
            }
        });
        
        setCityInfoType(mCityInfoType);
        setWheelType(mWheelType);
    }
    
    /**
     * 重置属性
     */
    private void reset() {
        textColor = "#585858";
        textSize = 18;
        visibleItems = 5;
        isProvinceCyclic = true;
        isCityCyclic = true;
        isDistrictCyclic = true;
        padding = 5;
        cancelTextColorStr = "#000000";
        confirmTextColorStr = "#0000FF";
        titleBackgroundColorStr = "#E9E9E9";
        titleTextColorStr = "#585858";
        defaultProvinceName = "江苏";
        defaultCityName = "常州";
        defaultDistrict = "新北区";
        mTitle = "选择地区";
        
        mCityInfoType = CityConfig.CityInfoType.BASE;
        mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
        
        setCityInfoType(mCityInfoType);
        setWheelType(mWheelType);
        
        mProCyclicCk.setChecked(true);
        mCityCyclicCk.setChecked(true);
        mAreaCyclicCk.setChecked(true);
        
        mTitleEt.setText("" + mTitle);
        mTitleColorEt.setText("" + titleTextColorStr);
        mTitleBgEt.setText("" + titleBackgroundColorStr);
        mItemTextSizeEt.setText("" + textSize);
        mItemTextColorEt.setText("" + textColor);
        mProEt.setText("" + defaultProvinceName);
        mCityEt.setText("" + defaultCityName);
        mAreaEt.setText("" + defaultDistrict);
        mConfirmTextColorEt.setText("" + confirmTextColorStr);
        mCancelTextColorEt.setText("" + cancelTextColorStr);
        mProVisibleCountEt.setText("" + visibleItems);
        mItemPaddingEt.setText("" + padding);
        
        setWheelType(mWheelType);
        
        setCityInfoType(mCityInfoType);
        
    }
    
    private void setCityInfoType(CityConfig.CityInfoType infoType) {
        if (infoType == CityConfig.CityInfoType.BASE) {
            mBaseTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mDetailTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mBaseTv.setTextColor(Color.parseColor("#ffffff"));
            mDetailTv.setTextColor(Color.parseColor("#333333"));
        }
        else {
            mBaseTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mDetailTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mBaseTv.setTextColor(Color.parseColor("#333333"));
            mDetailTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }
    
    /**
     *
     * @param wheelType
     */
    private void setWheelType(CityConfig.WheelType wheelType) {
        if (wheelType == CityConfig.WheelType.PRO) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#ffffff"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        }
        else if (wheelType == CityConfig.WheelType.PRO_CITY) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        }
        else {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }
    
    /**
     * 弹出选择器
     */
    private void wheel() {
        
        mTitle = mTitleEt.getText().toString();
        titleBackgroundColorStr = mTitleBgEt.getText().toString();
        textSize = Integer.parseInt(mItemTextSizeEt.getText().toString());
        titleTextColorStr = mTitleColorEt.getText().toString();
        textColor = mItemTextColorEt.getText().toString();
        
        defaultProvinceName = mProEt.getText().toString();
        defaultCityName = mCityEt.getText().toString();
        defaultDistrict = mAreaEt.getText().toString();
        
        confirmTextColorStr = mConfirmTextColorEt.getText().toString();
        cancelTextColorStr = mCancelTextColorEt.getText().toString();
        visibleItems = (Integer.parseInt(mProVisibleCountEt.getText().toString()));
        padding = (Integer.parseInt(mItemPaddingEt.getText().toString()));
        
        CityConfig cityConfig = new CityConfig.Builder(CitypickerWheelActivity.this)
                .title("选择地区")
                .titleBackgroundColor("#E9E9E9")
                .textSize(18)
                .titleTextColor("#585858")
                .textColor("0xFF585858")
                .confirTextColor("#0000FF")
                .cancelTextColor("#000000")
                .province("江苏")
                .city("常州")
                .district("新北区")
                .visibleItemsCount(5)
                .provinceCyclic(true)
                .cityCyclic(true)
                .districtCyclic(true)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .build();
        
        CityPickerView cityPicker = new CityPickerView(cityConfig);
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                
                if (district != null) {
                    //返回结果
                    mResultTv.setText(
                            "所选城市：\n" + province.toString() + "\n" + city.toString() + "\n" + district.toString());
                }
                else {
                    //返回结果
                    mResultTv.setText("所选城市：\n" + province.toString() + "\n" + city.toString());
                }
                
            }
            
            @Override
            public void onCancel() {
                
            }
        });
    }
}
