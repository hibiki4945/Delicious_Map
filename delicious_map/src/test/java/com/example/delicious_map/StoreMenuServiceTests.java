package com.example.delicious_map;

import com.example.delicious_map.entity.Menu;
import com.example.delicious_map.entity.Store;
import com.example.delicious_map.repository.StoreDao;
import com.example.delicious_map.service.ifs.StoreMenuService;
import com.example.delicious_map.vo.MenuResponse;
import com.example.delicious_map.vo.StoreMenuJoinResponse;
import com.example.delicious_map.vo.StoreMenuVo;
import com.example.delicious_map.vo.StoreResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class StoreMenuServiceTests {

    @Autowired
    private StoreMenuService storeMenuService;
    
    @Autowired
    private StoreDao storeDao;
    
    @Test
    public void addStore() {
        Store store = new Store("store4", "Tainan", 0);
        StoreResponse res = storeMenuService.addStore(store);
        
        Assert.isTrue(res.getCode() == "200", res.getMessage());
        
        for (Store item : res.getStoreList()) {
            System.out.printf("StoreName: %s, City: %s, Evaluate: %d\n", item.getStoreName(), item.getCity(), item.getEvaluate());
        }
        
    }
    
    @Test
    public void addMenu() {
        Menu menu = new Menu("menu3", 100, 0, "store1");
        MenuResponse res = storeMenuService.addMenu(menu);
        Assert.isTrue(res.getCode() == "200", res.getMessage());

        for (Menu item : res.getMenuList()) {
            System.out.printf("dish_name: %s, dish_price: %d, dish_evaluate: %s, store_name: %s\n", item.getDishName(), item.getDishPrice(), item.getDishEvaluate(), item.getStoreName());
        }
        
    }
    
    @Test
    public void changeMenu() {
        Menu menu = new Menu("menu1", 200, 0, "store1");
        MenuResponse res = storeMenuService.changeMenu(menu);
        Assert.isTrue(res.getCode() == "200", "Failed");
    }
    
    @Test
    public void justJoin() {
//        Menu menu = new Menu("menu1", 200, 0, "store1");
        StoreMenuJoinResponse res = storeMenuService.justJoin();
//        System.out.println("123");
        Assert.isTrue(res.getCode() == "200", "Failed");
        for (StoreMenuVo item : res.getStoreMenuList()) {
            System.out.printf("store_name: %s, city: %s, evaluate: %d, dish_name: %s, dish_price: %d, dish_evaluate: %d\n", item.getStoreName(), item.getCity(), item.getEvaluate(), item.getDishName(), item.getDishPrice(), item.getDishEvaluate());
        }
        
    }

    @Test
    public void findCityStoreLimitTest() {

        ObjectMapper objectMapper = new ObjectMapper();
        StoreMenuJoinResponse res = storeMenuService.findCityStoreLimit("Tainan", 2);
//      System.out.println("123");
      Assert.isTrue(res.getCode() == "200", "Failed");
      
//      for (StoreMenuVo item : res.getStoreMenuList()) {
//          System.out.printf("store_name: %s, city: %s, evaluate: %d, dish_name: %s, dish_price: %d, dish_evaluate: %d\n", item.getStoreName(), item.getCity(), item.getEvaluate(), item.getDishName(), item.getDishPrice(), item.getDishEvaluate());
//      }
      
      try {
          String resJson = objectMapper.writeValueAsString(res.getStoreMenuList());
          System.out.printf("%s, %s\n", res.getMessage(), resJson);
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      
    }
    
    
    @Test
    public void findStoreEvaluateGreaterThanTest() {
        
        StoreMenuJoinResponse res = storeMenuService.findStoreEvaluateGreaterThan(1);
//      System.out.println("123");
      Assert.isTrue(res.getCode() == "200", "Failed");
      for (StoreMenuVo item : res.getStoreMenuList()) {
          System.out.printf("store_name: %s, city: %s, evaluate: %d, dish_name: %s, dish_price: %d, dish_evaluate: %d\n", item.getStoreName(), item.getCity(), item.getEvaluate(), item.getDishName(), item.getDishPrice(), item.getDishEvaluate());
      }
      
    }

    @Test
    public void findStoreEvaluateGreaterThanAndMenuEvaluateGreaterThan() {

        ObjectMapper objectMapper = new ObjectMapper();
        StoreMenuJoinResponse res = storeMenuService.findStoreEvaluateGreaterThanAndMenuEvaluateGreaterThan(1, 1);
//      System.out.println("123");
      Assert.isTrue(res.getCode() == "200", "Failed");
      
      try {
          String resJson = objectMapper.writeValueAsString(res.getStoreMenuList());
          System.out.printf("%s, %s\n", res.getMessage(), resJson);
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      
    }

    
}
