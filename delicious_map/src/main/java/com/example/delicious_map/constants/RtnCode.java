package com.example.delicious_map.constants;

public enum RtnCode {

    SUCCESSFUL("200", "successful!"),
    DATA_ERROR("400", "data error!"),
    STORE_DUPLICATE("400", "store duplicate!"),
    DISH_COUNT_LIMIT("400", "dish count limit!"),
    DISH_NO_FOUND("400", "dish no found!");
    
    private String code;
    
    private String message;

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

    private RtnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
}

