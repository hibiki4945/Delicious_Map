package com.example.delicious_map.service.ifs;

import com.example.delicious_map.entity.Menu;
import com.example.delicious_map.entity.Store;
import com.example.delicious_map.vo.MenuResponse;
import com.example.delicious_map.vo.StoreMenuJoinResponse;
import com.example.delicious_map.vo.StoreMenuResponse;
import com.example.delicious_map.vo.StoreResponse;

public interface StoreMenuService {

    public StoreResponse addStore(Store store);
    
    public MenuResponse addMenu(Menu menu);
    
    public MenuResponse changeMenu(Menu menu);
    
    public StoreMenuResponse changeMenuEvaluate(Menu menu);

    public StoreMenuJoinResponse justJoin();
    
    public StoreMenuJoinResponse findCityStoreLimit(String City, int limit);
    
    public StoreMenuJoinResponse findStoreEvaluateGreaterThan(int Evaluate);
    
    public StoreMenuJoinResponse findStoreEvaluateGreaterThanAndMenuEvaluateGreaterThan(int evaluate, int dishEvaluate);
    
}
