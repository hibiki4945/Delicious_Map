package com.example.delicious_map.vo;

import com.example.delicious_map.entity.Menu;
import com.example.delicious_map.entity.Store;

import java.util.List;

public class StoreMenuResponse {

    private String code;
    
    private String message;
    
    private List<Store> storeList;
    
    private List<Menu> menuList;

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

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public StoreMenuResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public StoreMenuResponse(String code, String message, List<Store> storeList, List<Menu> menuList) {
        super();
        this.code = code;
        this.message = message;
        this.storeList = storeList;
        this.menuList = menuList;
    }
    
}
