package com.example.delicious_map.vo;

import com.example.delicious_map.entity.Store;

import java.util.List;

public class StoreResponse {

    private String code;
    
    private String message;
    
    private List<Store> storeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public StoreResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public StoreResponse(String code, String message, List<Store> storeList) {
        super();
        this.code = code;
        this.message = message;
        this.storeList = storeList;
    }
    
}
