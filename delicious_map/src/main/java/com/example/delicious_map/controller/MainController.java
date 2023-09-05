package com.example.delicious_map.controller;

import com.example.delicious_map.entity.Menu;
import com.example.delicious_map.entity.Store;
import com.example.delicious_map.service.ifs.StoreMenuService;
import com.example.delicious_map.vo.EvaluateGreaterThan2Req;
import com.example.delicious_map.vo.EvaluateGreaterThanReq;
import com.example.delicious_map.vo.MenuRequest;
import com.example.delicious_map.vo.MenuResponse;
import com.example.delicious_map.vo.StoreMenuJoinResponse;
import com.example.delicious_map.vo.StoreMenuResponse;
import com.example.delicious_map.vo.StoreRequest;
import com.example.delicious_map.vo.StoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private StoreMenuService storeMenuService;
    
    @PostMapping(value = "store/add_info")
    public StoreResponse addStore(@RequestBody StoreRequest req) {
        Store store = new Store(req.getStoreName(), req.getCity(), 0);
        List<Store> storeList = new ArrayList<Store>(Arrays.asList(store));
        StoreResponse res = storeMenuService.addStore(store);
        return res;
        
    }
    
    @PostMapping(value = "menu/add_info")
    public MenuResponse addMenu(@RequestBody MenuRequest req) {
        Menu menu = new Menu(req.getDishName(), req.getDishPrice(), req.getDishEvaluate(), req.getStoreName());
        List<Menu> menuList = new ArrayList<Menu>(Arrays.asList(menu));
        MenuResponse res = storeMenuService.addMenu(menu);
        return res;
          
    }
    
    @PostMapping(value = "menu/change_info")
    public MenuResponse changeMenu(@RequestBody MenuRequest req) {
        Menu menu = new Menu(req.getDishName(), req.getDishPrice(), req.getDishEvaluate(), req.getStoreName());
        List<Menu> menuList = new ArrayList<Menu>(Arrays.asList(menu));
        MenuResponse res = storeMenuService.changeMenu(menu);
        return res;
        
    }
    
    @PostMapping(value = "menu/change_evaluate")
    public StoreMenuResponse changeMenuEvaluate(@RequestBody MenuRequest req) {
        Menu menu = new Menu(req.getDishName(), req.getDishPrice(), req.getDishEvaluate(), req.getStoreName());
        List<Menu> menuList = new ArrayList<Menu>(Arrays.asList(menu));
        StoreMenuResponse res = storeMenuService.changeMenuEvaluate(menu);
        
        return res;
        
    }
    
    @PostMapping(value = "menu/findCityStoreLimit")
    public StoreMenuJoinResponse findCityStoreLimit(@RequestBody StoreRequest req) {
//        Store store = new Store(req.getStoreName(), req.getCity(), 0);
        StoreMenuJoinResponse res = storeMenuService.findCityStoreLimit(req.getCity(), req.getLimit());
        
        return res;
        
    }
    
    @PostMapping(value = "menu/findStoreEvaluateGreaterThan")
    public StoreMenuJoinResponse findStoreEvaluateGreaterThan(@RequestBody EvaluateGreaterThanReq req) {
//        Store store = new Store(req.getStoreName(), req.getCity(), 0);
        StoreMenuJoinResponse res = storeMenuService.findStoreEvaluateGreaterThan(req.getEvaluate());
        
        return res;
        
    }
    
    @PostMapping(value = "menu/findStoreEvaluateGreaterThan2")
    public StoreMenuJoinResponse findStoreEvaluateGreaterThan2(@RequestBody EvaluateGreaterThan2Req req) {
//        Store store = new Store(req.getStoreName(), req.getCity(), 0);
        StoreMenuJoinResponse res = storeMenuService.findStoreEvaluateGreaterThanAndMenuEvaluateGreaterThan(req.getEvaluate(), req.getDishEvaluate());
        
        return res;
        
    }
    
}
