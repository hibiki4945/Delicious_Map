package com.example.delicious_map.repository;

import com.example.delicious_map.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuDao extends JpaRepository<Menu, String>{

    @Modifying
    @Transactional
    @Query(value = "insert into menu (dish_name, dish_price, dish_evaluate, store_name) "
                   + " select :inputMenuName, :inputMenuPrice, :inputMenuEvaluate, :inputStoreName "
//                   + " where not exists (select 1 from menu where dish_name = :inputMenuName)", nativeQuery = true)
                   + " where not exists (select 1 from menu where dish_name = :inputMenuName)", nativeQuery = true)
    public int insertMenuInfo(@Param("inputMenuName") String dishName, @Param("inputMenuPrice") int dishPrice, @Param("inputMenuEvaluate") int dishEvaluate, @Param("inputStoreName") String storeName);

    @Modifying(clearAutomatically = true)
    @Transactional // insert & update & delete 都需加此行
    @Query(value = "update menu set dish_price = :newDishPrice where dish_name = :newDishName", nativeQuery = true)
    public int updateMenuInfo(@Param("newDishName") String dishName, @Param("newDishPrice") int dishPrice);

//    @Query(value = "SELECT count(dish_name) AS dish_count FROM menu WHERE store_name = :inputStoreName ")
//    @Query(value = "SELECT new Menu(dish_name, dish_price, dish_evaluate, store_name) FROM menu WHERE store_name = :inputStoreName ", nativeQuery = true)
    @Query(value = "SELECT * FROM menu WHERE store_name = :inputStoreName ", nativeQuery = true)
    public List<Menu> countMenuInfo(@Param("inputStoreName") String storeName);
    
    public List<Menu> findAllByStoreName(String storeName);
    
    @Modifying(clearAutomatically = true)
    @Transactional // insert & update & delete 都需加此行
    @Query(value = "update menu set dish_evaluate = :newDishEvaluate where store_name = :newStoreName and dish_name = :newDishName ", nativeQuery = true)
    public int updateMenuEvaluate(@Param("newStoreName") String storeName, @Param("newDishName") String dishName, @Param("newDishEvaluate") int dishEvaluate);

    @Query(value = "SELECT * FROM menu WHERE store_name = :inputStoreName and dish_name = :inputDishName", nativeQuery = true)
    public List<Menu> findMenuInfoByStoreNameAndDishName(@Param("inputStoreName") String storeName, @Param("inputDishName") String dishName);
    
}
