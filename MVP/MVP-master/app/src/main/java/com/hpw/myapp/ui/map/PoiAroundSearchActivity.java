package com.hpw.myapp.ui.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.AppUtils;
import com.hpw.mvpframe.utils.DisplayUtils;
import com.hpw.mvpframe.utils.StatusBarUtil;
import com.hpw.mvpframe.utils.ToastUtils;
import com.hpw.myapp.Constants;
import com.hpw.myapp.R;
import com.hpw.myapp.widget.scrolllayout.ScrollLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hpw on 2017/1/24.
 */

public class PoiAroundSearchActivity extends CoreBaseActivity implements View.OnClickListener, AMap.OnCameraChangeListener,
        PoiSearch.OnPoiSearchListener, LocationSource, AMapLocationListener, TextWatcher, Inputtips.InputtipsListener {
    private MapView mapview;
    private ListView listView;
    private AMap mAMap;
    private PoiResult poiResult; // poi返回的结果
    private PoiSearch.Query query;// Poi查询条件类
    private LatLonPoint lp = new LatLonPoint(39.993743, 116.472995);// 116.472995,39.993743
    private PoiSearch poiSearch;
    private static List<PoiItem> poiItems = new ArrayList<>();// poi数据
    private AutoCompleteTextView mSearchText;
    private ScrollLayout mScrollLayout;
    private AMapLocationClient mlocationClient;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    private ImageView mLocation;
    private Boolean isLocation = false;
    private static AMapLocation mAMapLocation;
    private ListviewAdapter listviewAdapter;
    public final static int REQUESTCODE = 1;
    public final static int RESULTCODE = 2;

    @Override
    public int getLayoutId() {
        return R.layout.poiaroundsearch_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mapview = (MapView) findViewById(R.id.mapView);
        mapview.onCreate(savedInstanceState);
        initView();
        init();
    }

    private void initView() {
        StatusBarUtil.setTransparent(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root);
        mScrollLayout = (ScrollLayout) findViewById(R.id.scroll_down_layout);

        /**设置 setting*/
        mScrollLayout.setMinOffset(DisplayUtils.dp2px(this, 50));
        mScrollLayout.setMaxOffset(DisplayUtils.getScreenHeightPixels(this) - DisplayUtils.dp2px(this, 320));
        mScrollLayout.setIsSupportExit(false);
        mScrollLayout.setAllowHorizontalScroll(true);
        mScrollLayout.getBackground().setAlpha(0);
        relativeLayout.setOnClickListener(v -> mScrollLayout.scrollToExit());
        mLocation = (ImageView) findViewById(R.id.location);
        mLocation.setOnClickListener(this);
    }

    private void scroll() {
        mScrollLayout.setToOpen();
        listView = (ListView) findViewById(R.id.list_view);
        listviewAdapter = new ListviewAdapter(this, poiItems);
        listView.setAdapter(listviewAdapter);
        if (poiItems != null && poiItems.size() != 0 && isLocation) {
            LatLonPoint latLonPoint = poiItems.get(0).getLatLonPoint();
            mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude()), 17));
        }
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (mAMap == null) {
            mAMap = mapview.getMap();
            mAMap.setLocationSource(this);
            mAMap.getUiSettings().setZoomControlsEnabled(false);
            mAMap.setMyLocationEnabled(true);
            mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            mAMap.setOnCameraChangeListener(this);
            TextView searchButton = (TextView) findViewById(R.id.search_button);
            searchButton.setOnClickListener(this);
            searchButton.setText(getString(R.string.search));
        }
        setup();
        setupLocationStyle();
    }

    private void setup() {
        mSearchText = (AutoCompleteTextView) findViewById(R.id.etInput);
        mSearchText.addTextChangedListener(this);
        mSearchText.setHint(getString(R.string.search_map));
        findViewById(R.id.search_back).setOnClickListener(view -> {
            finish();
        });
        mSearchText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                doSearch();
            }
            return false;
        });
    }

    /**
     * 设置自定义定位蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(getResources().getColor(R.color.map));
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(2);
        // 设置圆形的填充颜色
//        myLocationStyle.radiusFillColor(Color.argb(10, 0, 0, 180));
        myLocationStyle.radiusFillColor(getResources().getColor(R.color.map_fill));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keyWord, String cityCode, boolean is) {
        isLocation = is;
        query = new PoiSearch.Query(keyWord, "", cityCode);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            if (!is) {
                poiSearch.setBound(new PoiSearch.SearchBound(lp, 1000));
            }
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        scroll();
                    } else if (suggestionCities != null && suggestionCities.size() > 0) {
                        doSearchQuery(mSearchText.getText().toString().trim(), suggestionCities.get(0).getCityCode(), true);
                    } else {
                        ToastUtils.showToast(PoiAroundSearchActivity.this,
                                getString(R.string.no_result));
                    }
                }
            } else {
                ToastUtils.showToast(PoiAroundSearchActivity.this, getString(R.string.no_result));
            }
        } else {
            ToastUtils.showToast(this.getApplicationContext(), String.valueOf(rcode));
        }
    }

    //定位有关接口
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mAMapLocation = aMapLocation;
                mlocationClient.stopLocation();
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                lp = new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lp.getLatitude(), lp.getLongitude()), 17));
                doSearchQuery(aMapLocation.getPoiName().trim(), aMapLocation.getCityCode(), false);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                ToastUtils.showToast(this, errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            List<HashMap<String, String>> listString = new ArrayList<>();
            for (int i = 0; i < tipList.size(); i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", tipList.get(i).getName());
                map.put("address", tipList.get(i).getDistrict());
                listString.add(map);
            }
            SimpleAdapter aAdapter = new SimpleAdapter(this, listString, R.layout.item_find_city_search,
                    new String[]{"name", "address"}, new int[]{R.id.poi_name, R.id.poi_address});

            mSearchText.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
            mSearchText.setOnItemClickListener((adapterView, view, position, id) -> {
                mSearchText.setText(listString.get(position).get("name"));
                Intent intent = new Intent();
                intent.putExtra(Constants.ARG_POIITEM, listString.get(position).get("name"));
                setResult(RESULTCODE, intent);
//                finish();
                showToast(listString.get(position).get("name"));
            });
        } else {
            ToastUtils.showToast(this.getApplicationContext(), String.valueOf(rCode));
        }
    }

    //自动提示接口
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String newText = charSequence.toString().trim();
        InputtipsQuery inputquery = new InputtipsQuery(newText, null);
        Inputtips inputTips = new Inputtips(this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                doSearch();
                break;
            case R.id.location:
                mlocationClient.startLocation();
                break;
            default:
                break;
        }

    }

    private void doSearch() {
        AppUtils.hideSoftInput(mSearchText);
        doSearchQuery(mSearchText.getText().toString().trim(), mAMapLocation.getCityCode(), true);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;
        lp = new LatLonPoint(target.latitude, target.longitude);
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getRegeocodeAddress() != null
                            && result.getRegeocodeAddress().getPois() != null) {
                        listviewAdapter.setData(result.getRegeocodeAddress().getPois());
                    }
                } else {
                    ToastUtils.showToast(mContext, String.valueOf(rCode));
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        RegeocodeQuery query = new RegeocodeQuery(lp, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    private class ListviewAdapter extends BaseAdapter {

        private Context mContext;
        private List<PoiItem> mPoiItem = new ArrayList<>();

        public ListviewAdapter(Context context, List<PoiItem> list) {
            this.mContext = context;
            this.mPoiItem = list;
        }

        public void setData(List<PoiItem> list) {
            if (list != null) {
                this.mPoiItem = list;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return mPoiItem.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder;
            if (null == convertView) {
                convertView = View.inflate(mContext, R.layout.item_find_city_search, null);
                viewholder = new ViewHolder(convertView);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            PoiItem mCurrentPoi = mPoiItem.get(position);
            viewholder.mPoiName.setText(mCurrentPoi.getTitle());
            viewholder.mPoiAddress.setText(mCurrentPoi.getSnippet() + mCurrentPoi.getDistance());
            if (position == 0) {
                viewholder.mCurrent.setVisibility(View.VISIBLE);
            } else {
                viewholder.mCurrent.setVisibility(View.GONE);
            }
            if (position == mPoiItem.size() - 1 && mPoiItem.size() > 7) {
                viewholder.mSpace.setVisibility(View.VISIBLE);
            } else {
                viewholder.mSpace.setVisibility(View.GONE);
            }
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.putExtra(Constants.ARG_POIITEM, mCurrentPoi);
                setResult(RESULTCODE, intent);
//                finish();
                showToast(mCurrentPoi.getTitle());
            });
            return convertView;
        }

        class ViewHolder {
            TextView mPoiName, mPoiAddress;
            ImageView mCurrent;
            View mSpace;

            public ViewHolder(View view) {
                mPoiName = (TextView) view.findViewById(R.id.poi_name);
                mPoiAddress = (TextView) view.findViewById(R.id.poi_address);
                mCurrent = (ImageView) view.findViewById(R.id.iv_current);
                mSpace = view.findViewById(R.id.space);
            }
        }
    }
}