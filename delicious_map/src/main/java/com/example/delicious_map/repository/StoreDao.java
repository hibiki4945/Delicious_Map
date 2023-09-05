package com.example.delicious_map.repository;

import com.example.delicious_map.entity.Store;
import com.example.delicious_map.vo.StoreMenuVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StoreDao extends JpaRepository<Store, String>{

    @Modifying
    @Transactional
    @Query(value = "insert into store (store_name, city, evaluate) "
                   + " select :inputStoreName, :inputCity, :inputEvaluate "
                   + " where not exists (select 1 from store where store_name = :inputStoreName)", nativeQuery = true)
    public int insertStoreInfo(@Param("inputStoreName") String StoreName, @Param("inputCity") String City, @Param("inputEvaluate") int Evaluate);

    @Query(value = "SELECT * FROM store WHERE store_name = :inputStoreName", nativeQuery = true)
    public List<Store> findStoreInfoByStoreName(@Param("inputStoreName") String storeName);

    public Store findByStoreName(String storeName);
    
    @Modifying(clearAutomatically = true)
    @Transactional // insert & update & delete 都需加此行
    @Query(value = "update store set evaluate = :newEvaluate where store_name = :newStoreName ", nativeQuery = true)
    public int updateStoreEvaluate(@Param("newStoreName") String storeName, @Param("newEvaluate") int Evaluate);

    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, m.dishPrice, m.dishEvaluate) from Store as s "
          + " join Menu as m "
          + " on s.city = :inputCity ") 
    public List<StoreMenuVo> findStoreInfoByCity(@Param("inputCity")String City, PageRequest pageable);

    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, "
            + " case when m.dishPrice is null then 0 else m.dishPrice end, "
            + " case when m.dishEvaluate is null then 0 else m.dishEvaluate end) "
            + " from Store as s "
            + " left join Menu as m "
            + " on s.city = :inputCity and s.storeName = m.storeName ") 
    public List<StoreMenuVo> findStoreInfoByCity2(@Param("inputCity")String City, PageRequest pageable);

    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, "
            + " case when m.dishPrice is null then 0 else m.dishPrice end, "
            + " case when m.dishEvaluate is null then 0 else m.dishEvaluate end) "
            + " from Store as s "
            + " left join Menu as m "
            + " on s.city = :inputCity and s.storeName = m.storeName ") 
    public List<StoreMenuVo> findStoreInfoByCity3(@Param("inputCity")String City, Pageable pageable);
    
//    public Page<Store> findAll(Pageable pageable);
    
    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, m.dishPrice, m.dishEvaluate) from Store as s "
                 + " join Menu as m "
                 + " on s.storeName = m.storeName ") 
    public List<StoreMenuVo> joinStoreMenu();

    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, m.dishPrice, m.dishEvaluate) from Store as s "
                 + " join Menu as m "
                 + " on s.storeName = m.storeName and s.evaluate >= :inputEvaluate "
                 + " order by s.evaluate desc ") 
    public List<StoreMenuVo> findStoreInfoEvaluateGreaterThan(@Param("inputEvaluate") int evaluate);

    @Query(value = "select new com.example.delicious_map.vo.StoreMenuVo(s.storeName, s.city, s.evaluate, m.dishName, m.dishPrice, m.dishEvaluate) from Store as s "
                 + " join Menu as m "
                 + " on s.storeName = m.storeName and s.evaluate >= :inputEvaluate and m.dishEvaluate >= :inputDishEvaluate "
                 + " order by s.evaluate desc ") 
    public List<StoreMenuVo> findStoreInfoEvaluateGreaterThan2(@Param("inputEvaluate") int evaluate, @Param("inputDishEvaluate") int dishEvaluate);

//    public Page<StoreMenuVo> findStoreInfoByCity(PageRequest of, String string);

}



