package com.example.delicious_map.service.impl;

import com.example.delicious_map.constants.RtnCode;
import com.example.delicious_map.entity.Menu;
import com.example.delicious_map.entity.Store;
import com.example.delicious_map.repository.MenuDao;
import com.example.delicious_map.repository.StoreDao;
import com.example.delicious_map.service.ifs.StoreMenuService;
import com.example.delicious_map.vo.MenuResponse;
import com.example.delicious_map.vo.StoreMenuJoinResponse;
import com.example.delicious_map.vo.StoreMenuResponse;
import com.example.delicious_map.vo.StoreMenuVo;
import com.example.delicious_map.vo.StoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreMenuServiceImpl implements StoreMenuService{

    @Autowired
    private StoreDao storeDao;
    
    @Autowired
    private MenuDao menuDao;
    
    @Override
    public StoreResponse addStore(Store store) {
        // TODO Auto-generated method stub
//        storeDao.save(store);
      List<Store> storeList0 = null;
      int res = storeDao.insertStoreInfo(store.getStoreName(), store.getCity(), store.getEvaluate());
//      Assert.isTrue(res == 1, RtnCode.DATA_ERROR.getMessage());
      if(res != 1) {
          return new StoreResponse(RtnCode.STORE_DUPLICATE.getCode(), RtnCode.STORE_DUPLICATE.getMessage(), storeList0);
      }
        List<Store> storeList = new ArrayList(Arrays.asList(store));
        
        return new StoreResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), storeList);
    }

    @Override
    public MenuResponse addMenu(Menu menu) {
        // TODO Auto-generated method stub
        List<Menu> menuList0 = menuDao.countMenuInfo(menu.getStoreName());
        if(menuList0.size() >= 3) {
            return new MenuResponse(RtnCode.DISH_COUNT_LIMIT.getCode(), RtnCode.DISH_COUNT_LIMIT.getMessage(), menuList0);
        }
        int res = menuDao.insertMenuInfo(menu.getDishName(), menu.getDishPrice(), menu.getDishEvaluate(), menu.getStoreName());
        if(res != 1) {
            return new MenuResponse(RtnCode.DATA_ERROR.getCode(), RtnCode.DATA_ERROR.getMessage(), menuList0);
        }
        menuList0 = menuDao.countMenuInfo(menu.getStoreName());
        
        return new MenuResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), menuList0);
        
    }
    
    @Override
    public MenuResponse changeMenu(Menu menu) {
        // TODO Auto-generated method stub    
      List<Menu> menuList0 = new ArrayList<>(Arrays.asList());
      if(!menuDao.existsById(menu.getDishName())) {
          return new MenuResponse(RtnCode.DISH_NO_FOUND.getCode(), RtnCode.DISH_NO_FOUND.getMessage(), menuList0);
      }
      int res = menuDao.updateMenuInfo(menu.getDishName(), menu.getDishPrice());
      System.out.println(res);
      if(res != 1) {
          return new MenuResponse(RtnCode.DATA_ERROR.getCode(), RtnCode.DATA_ERROR.getMessage(), menuList0);
      }
      menuList0.add(menu);
      return new MenuResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), menuList0);
      
    }

    @Override
    public StoreMenuResponse changeMenuEvaluate(Menu menu) { 
//        menuDao.save(menu);
        Menu target = menu;
        String storeName = menu.getStoreName();
        String dishName = menu.getDishName();
        int evaluate = menu.getDishEvaluate();
        
//      餐點評價更改後，計算evaluate的平均值用
        List<Menu> menuList0 = new ArrayList<>();
//      此方法帶入之Menu變數
        List<Menu> menuList = new ArrayList<>(Arrays.asList(menu));
        
        
        int storeEvaluateCounter = 0,res=0;
//      回傳店家評價, 改變前
        List<Store> storeList0 = new ArrayList<>();
//      改變後 
        List<Store> storeList1 = new ArrayList<>();
//      將改變前與改變後放一起
        List<Store> storeList = new ArrayList<>();
        
        
//      改成 find by(store_name & dish_name)(再用 .get(取值 做判斷))
        menuList = menuDao.findMenuInfoByStoreNameAndDishName(storeName, dishName);
        
        if(menuList.isEmpty()) {
            return new StoreMenuResponse(RtnCode.DISH_NO_FOUND.getCode(), 
                    RtnCode.DISH_NO_FOUND.getMessage(), storeList, menuList);    
        }
        
        storeList0 = storeDao.findStoreInfoByStoreName(storeName);
        Store store0 = new Store();
        store0 = storeDao.findByStoreName(storeName);
        
        for (Store item : storeList0) {
            System.out.printf("(before)store_name: %s, city: %s, evaluate: %d\n", 
                    store0.getStoreName(), store0.getCity(), store0.getEvaluate());
            store0.setEvaluate(store0.getEvaluate());
            storeList.add(store0);
        }
        
        res = menuDao.updateMenuEvaluate(storeName, dishName, evaluate);
        if(res != 1) {
            return new StoreMenuResponse(RtnCode.DATA_ERROR.getCode(), 
                    RtnCode.DATA_ERROR.getMessage(), storeList, menuList);    
        }
        
//      findAllByStoreName
//      先找到店家的所有評價並加總        
        menuList0 = menuDao.countMenuInfo(menu.getStoreName());
//        menuList0 = menuDao.findAllByStoreName(storeName);
        for (Menu item : menuList0) {
            System.out.println(item.getDishEvaluate());
            storeEvaluateCounter += item.getDishEvaluate();
        }
        System.out.println("storeEvaluateCounter: "+storeEvaluateCounter);
        storeEvaluateCounter /= menuList0.size();
        System.out.println("storeEvaluateCounter: "+storeEvaluateCounter);
        
        res = storeDao.updateStoreEvaluate(menu.getStoreName(), storeEvaluateCounter);
        if(res != 1) {
            return new StoreMenuResponse(RtnCode.DATA_ERROR.getCode(), RtnCode.DATA_ERROR.getMessage(), storeList, menuList);    
        }
        
        storeList1 = storeDao.findStoreInfoByStoreName(menu.getStoreName());
        for (Store items : storeList1) {
            System.out.printf("(after)store_name: %s, city: %s, evaluate: %d\n", items.getStoreName(), items.getCity(), items.getEvaluate());
            storeList.add(items);
        }

        for (Menu item : menuList) {
            System.out.printf("menu.evaluate: %d\n", menu.getDishEvaluate());
            item.setDishEvaluate(menu.getDishEvaluate());
        }
        
        return new StoreMenuResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), storeList, menuList);
        
    }

    @Override
    public StoreMenuJoinResponse justJoin() {
        // TODO Auto-generated method stub
        List<StoreMenuVo> res = storeDao.joinStoreMenu();
//         StoreMenuVo res0 = new StoreMenuVo("store1", "city1", 0, "dish1", 0, 0);
//         List<StoreMenuVo> res = new ArrayList<>();
//         res.add(res0);
         
        return new StoreMenuJoinResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
    }
    
    @Override
    public StoreMenuJoinResponse findCityStoreLimit(String City, int limit) {
        // TODO Auto-generated method stub

//        List<Store> storeList1 = new ArrayList<>();
//        List<Store> storeList = new ArrayList<>();
//        List<Menu> menuList = new ArrayList<>();
//        List<Menu> menuList1 = new ArrayList<>();

//        List<StoreMenuVo> res = storeDao.findStoreInfoByCity(City, 0, limit);
//        List<StoreMenuVo> res = storeDao.findStoreInfoByCity(City);
//        List<StoreMenuVo> res = storeDao.findStoreInfoByCity(City, 0, limit);
        List<StoreMenuVo> res = new ArrayList<>();

//        Page<StoreMenuVo> res0 = storeDao.findStoreInfoByCity(PageRequest.of(0, limit), "Tainan");
        
//        for (StoreMenuVo item : res0) {
//            res.add(item);
//        }
        
        if(limit != 0) {
//            res = storeDao.findStoreInfoByCity("Tainan", PageRequest.of(0, limit));
            res = storeDao.findStoreInfoByCity3("Tainan", PageRequest.of(0, limit));
        }
        else {
//            res = storeDao.findStoreInfoByCity("Tainan", PageRequest.of(0, 2100000000));
            res = storeDao.findStoreInfoByCity3("Tainan", PageRequest.of(0, 2100000000));
        }
        
        
//        for (Store items : storeList1) {
//            System.out.printf("(after)store_name: %s, city: %s, evaluate: %d\n", items.getStoreName(), items.getCity(), items.getEvaluate());
//            storeList.add(items);
//        }
//        int count = 0;
//        for (Store items : storeList1) {
//            menuList1 = menuDao.countMenuInfo(items.getStoreName());
//            for (Menu menu : menuList1) {
//                count++;
//                if(limit == 0 || count <= limit) {
//                    menuList.add(menu);   
//                }
//                else {
//                    return new StoreMenuResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), storeList, menuList);
//                }
//            }
//        }
        
        return new StoreMenuJoinResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
        
    }

    @Override
    public StoreMenuJoinResponse findStoreEvaluateGreaterThan(int Evaluate) {
        // TODO Auto-generated method stub
//        List<Store> storeList1 = new ArrayList<>();
//        List<Store> storeList = new ArrayList<>();
//        List<Menu> menuList = new ArrayList<>();
//        List<Menu> menuList1 = new ArrayList<>();
//        
//        storeList1 = storeDao.findStoreInfoEvaluateGreaterThan(Evaluate);

//      List<StoreMenuVo> res = storeDao.joinStoreMenu();  
        List<StoreMenuVo> res = storeDao.findStoreInfoEvaluateGreaterThan(Evaluate);
//        List<StoreMenuVo> res = storeDao.findStoreInfoEvaluateGreaterThan();
   
//        for (Store items : storeList1) {
//            System.out.printf("(after)store_name: %s, city: %s, evaluate: %d\n", items.getStoreName(), items.getCity(), items.getEvaluate());
//            storeList.add(items);
//        }
//        for (Store items : storeList1) {
//            menuList1 = menuDao.countMenuInfo(items.getStoreName());
//            for (Menu menu : menuList1) {
//                if(menu.getDishEvaluate() > Evaluate) {
//                    menuList.add(menu);  
//                }
//            }
//        }

//        return new StoreMenuResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), storeList, menuList);
        return new StoreMenuJoinResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
        
    }

    @Override
    public StoreMenuJoinResponse findStoreEvaluateGreaterThanAndMenuEvaluateGreaterThan(int evaluate, int dishEvaluate) {
        // TODO Auto-generated method stub
//      List<StoreMenuVo> res = storeDao.joinStoreMenu();  
//        List<StoreMenuVo> res = storeDao.findStoreInfoEvaluateGreaterThan2(evaluate, dishEvaluate);
        List<StoreMenuVo> res = storeDao.findStoreInfoEvaluateGreaterThan2(evaluate, dishEvaluate);
        
        return new StoreMenuJoinResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), res);
        
    }


}
