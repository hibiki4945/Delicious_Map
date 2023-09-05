package com.example.delicious_map.vo;

import java.util.List;

public class StoreMenuJoinResponse {

        private String code;
        
        private String message;
        
        private List<StoreMenuVo> storeMenuList;

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

        public List<StoreMenuVo> getStoreMenuList() {
            return storeMenuList;
        }

        public void setStoreMenuList(List<StoreMenuVo> storeMenuList) {
            this.storeMenuList = storeMenuList;
        }

        public StoreMenuJoinResponse() {
            super();
            // TODO Auto-generated constructor stub
        }

        public StoreMenuJoinResponse(String code, String message, List<StoreMenuVo> storeMenuList) {
            super();
            this.code = code;
            this.message = message;
            this.storeMenuList = storeMenuList;
        }

}
