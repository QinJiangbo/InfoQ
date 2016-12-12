package com.qinjiangbo.ofo;

/**
 * @date: 12/12/2016 6:40 PM
 * @author: qinjiangbo@github.io
 */
public class Address {

    private Double lat; // 经度
    private Double lng; // 纬度
    private String name; // 地名

    public Address(String name, Double lng, Double lat) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
