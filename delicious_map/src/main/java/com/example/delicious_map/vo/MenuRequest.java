package com.example.delicious_map.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuRequest {

    @JsonProperty("dish_name")
    private String dishName;

    @JsonProperty("dish_price")
    private int dishPrice;

    @JsonProperty("dish_evaluate")
    private int dishEvaluate;

    @JsonProperty("store_name")
    private String storeName;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishEvaluate() {
        return dishEvaluate;
    }

    public void setDishEvaluate(int dishEvaluate) {
        this.dishEvaluate = dishEvaluate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
}
