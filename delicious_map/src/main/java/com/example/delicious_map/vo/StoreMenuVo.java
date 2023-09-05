package com.example.delicious_map.vo;

public class StoreMenuVo {

    private String storeName;
    
    private String city;
    
    private int evaluate;
    
    private String dishName;
    
    private int dishPrice;
    
    private int dishEvaluate;

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

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

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

    public StoreMenuVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public StoreMenuVo(String storeName, String city, int evaluate, String dishName, int dishPrice, int dishEvaluate) {
        super();
        this.storeName = storeName;
        this.city = city;
        this.evaluate = evaluate;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishEvaluate = dishEvaluate;
    }
    
}
