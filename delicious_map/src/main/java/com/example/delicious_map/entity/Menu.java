package com.example.delicious_map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "dish_name")
    private String dishName;
    
    @Column(name = "dish_price")
    private int dishPrice;
    
    @Column(name = "dish_evaluate")
    private int dishEvaluate;
    
    @Column(name = "store_name")
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

    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Menu(String dishName, int dishPrice, int dishEvaluate, String storeName) {
        super();
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishEvaluate = dishEvaluate;
        this.storeName = storeName;
    }
    
}
