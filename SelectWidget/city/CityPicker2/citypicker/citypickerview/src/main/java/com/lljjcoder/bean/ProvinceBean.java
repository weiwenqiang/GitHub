package com.lljjcoder.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @2Do:
 * @Author M2
 * @Version v ${VERSION}
 * @Date 2017/7/7 0007.
 */
public class ProvinceBean implements Parcelable {

  private String id; /*110101*/

  private String name; /*东城区*/

  private String pinYin; /*Dongcheng*/

  private Double gisGcj02Lat; /*39.9288*/

  private Double gisGcj02Lng; /*116.416*/

  private Double gisBd09Lat; /*39.935*/

  private Double gisBd09Lng; /*116.422*/

  private String zipcode;

  private ArrayList<CityBean> cityList;

  public String getId() {
    return id == null ? "" : id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name == null ? "" : name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPinYin() {
    return pinYin == null ? "" : pinYin;
  }

  public void setPinYin(String pinYin) {
    this.pinYin = pinYin;
  }

  public Double getGisGcj02Lat() {
    return gisGcj02Lat == null ? new Double(0.0d) : gisGcj02Lat;
  }

  public void setGisGcj02Lat(Double gisGcj02Lat) {
    this.gisGcj02Lat = gisGcj02Lat;
  }

  public Double getGisGcj02Lng() {
    return gisGcj02Lng == null ? new Double(0.0d) : gisGcj02Lng;
  }

  public void setGisGcj02Lng(Double gisGcj02Lng) {
    this.gisGcj02Lng = gisGcj02Lng;
  }

  public Double getGisBd09Lat() {
    return gisBd09Lat == null ? new Double(0.0d) : gisBd09Lat;
  }

  public void setGisBd09Lat(Double gisBd09Lat) {
    this.gisBd09Lat = gisBd09Lat;
  }

  public Double getGisBd09Lng() {
    return gisBd09Lng == null ? new Double(0.0d) : gisBd09Lng;
  }

  public void setGisBd09Lng(Double gisBd09Lng) {
    this.gisBd09Lng = gisBd09Lng;
  }

  public String getZipcode() {
    return zipcode == null ? "" : zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public ArrayList<CityBean> getCityList() {
    return cityList;
  }

  public void setCityList(ArrayList<CityBean> cityList) {
    this.cityList = cityList;
  }

  @Override
  public String toString() {
    return  name ;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.name);
    dest.writeString(this.pinYin);
    dest.writeValue(this.gisGcj02Lat);
    dest.writeValue(this.gisGcj02Lng);
    dest.writeValue(this.gisBd09Lat);
    dest.writeValue(this.gisBd09Lng);
    dest.writeString(this.zipcode);
    dest.writeList(this.cityList);
  }

  public ProvinceBean() {
  }

  protected ProvinceBean(Parcel in) {
    this.id = in.readString();
    this.name = in.readString();
    this.pinYin = in.readString();
    this.gisGcj02Lat = (Double) in.readValue(Double.class.getClassLoader());
    this.gisGcj02Lng = (Double) in.readValue(Double.class.getClassLoader());
    this.gisBd09Lat = (Double) in.readValue(Double.class.getClassLoader());
    this.gisBd09Lng = (Double) in.readValue(Double.class.getClassLoader());
    this.zipcode = in.readString();
    this.cityList = new ArrayList<CityBean>();
    in.readList(this.cityList, CityBean.class.getClassLoader());
  }

  public static final Parcelable.Creator<ProvinceBean> CREATOR = new Parcelable.Creator<ProvinceBean>() {
    @Override
    public ProvinceBean createFromParcel(Parcel source) {
      return new ProvinceBean(source);
    }

    @Override
    public ProvinceBean[] newArray(int size) {
      return new ProvinceBean[size];
    }
  };
}
