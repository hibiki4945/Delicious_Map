package com.example.delicious_map.vo;

import com.example.delicious_map.entity.Menu;

import java.util.List;

public class MenuResponse {

    private String code;
    
    private String message;
    
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

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public MenuResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MenuResponse(String code, String message, List<Menu> menuList) {
        super();
        this.code = code;
        this.message = message;
        this.menuList = menuList;
    }
    
}
