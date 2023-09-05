package com.example.delicious_map.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreRequest {

    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("city")
    private String city;
    
    @JsonProperty("limit")
    private int limit;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    
}
