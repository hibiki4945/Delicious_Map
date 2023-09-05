package com.example.delicious_map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_name")
    private String storeName;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "evaluate")
    private int evaluate;

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

    public Store() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Store(String storeName, String city, int evaluate) {
        super();
        this.storeName = storeName;
        this.city = city;
        this.evaluate = evaluate;
    }
    
}



