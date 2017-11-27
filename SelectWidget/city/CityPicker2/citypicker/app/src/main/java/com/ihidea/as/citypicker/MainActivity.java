package com.ihidea.as.citypicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ihidea.as.citypicker.activity.CitypickerListActivity;
import com.ihidea.as.citypicker.activity.CitypickerWheelActivity;
import com.ihidea.as.citypicker.adapter.CityPickerAdapter;
import com.ihidea.as.citypicker.model.CityPickerStyleBean;
import com.ihidea.as.citypicker.utils.ActivityUtils;
import com.ihidea.as.citypicker.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    
    RecyclerView mCitypickerRv;
    
    CityPickerAdapter mCityPickerAdapter;
    
    String[] mTitle = new String[] { "城市列表", "滚轮实现" };
    
    Integer[] mIcon = new Integer[] { R.drawable.ic_list, R.drawable.ic_wheel };
    
    private List<CityPickerStyleBean> mCityPickerStyleBeanList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setData();
        initRecyclerView();
        

    }

    private void findView() {
        mCitypickerRv=(RecyclerView)findViewById(R.id.citypicker_rv);
    }

    private void setData() {
        for (int i = 0; i < mTitle.length; i++) {
            CityPickerStyleBean cityPickerStyleBean = new CityPickerStyleBean();
            cityPickerStyleBean.setTitle(mTitle[i]);
            cityPickerStyleBean.setResourId(mIcon[i]);
            mCityPickerStyleBeanList.add(cityPickerStyleBean);
        }
    }
    
    private void initRecyclerView() {
        
        mCitypickerRv.addItemDecoration(new DividerGridItemDecoration(this));
        mCitypickerRv.setLayoutManager(new GridLayoutManager(this, 3));

        mCityPickerAdapter = new CityPickerAdapter(MainActivity.this, mCityPickerStyleBeanList);
        mCitypickerRv.setAdapter(mCityPickerAdapter);
        mCityPickerAdapter.setmOnItemClickListener(new CityPickerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                
                if (null != mCityPickerAdapter && null != mCityPickerAdapter.getData() && !mCityPickerAdapter.getData().isEmpty()
                        && null != mCityPickerAdapter.getData().get(position)) {
                    gotoDetail(mCityPickerAdapter.getData().get(position).getResourId());
                }
            }
        });
        
    }
    
    /**
     * 选择相关样式
     * @param resourId
     */
    private void gotoDetail(int resourId) {
        switch (resourId) {
            case R.drawable.ic_list:
                ActivityUtils.getInstance().showActivity(MainActivity.this, CitypickerListActivity.class);
                break;
            
            case R.drawable.ic_wheel:
                ActivityUtils.getInstance().showActivity(MainActivity.this, CitypickerWheelActivity.class);
                break;
            
            default:
                break;
        }
    }

    
}
