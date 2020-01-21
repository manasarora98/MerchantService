package com.example.MerchantService.repositories;

import com.example.MerchantService.entity.ProductList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductListRepository  extends CrudRepository<ProductList,Integer> {

    @Modifying
    @Query(value = "UPDATE product_list SET price= :price, stock= :stock where merchant_id=:merchantid and product_id=:productid",nativeQuery = true)
    int update(@Param("price") Double price, @Param("stock") Integer stock, @Param("merchantid") Integer merchantid, @Param("productid") String productid);

    List<ProductList> findAllByProductId(String id);

    @Query(value = "Update product_list set stock =?1 where user_id =?2 and merchant_id=?3",nativeQuery=true)
    void updateQuantity(int stock, String userId,String merchantId);

   //  List<ProductList> findByMerchantIdAndProductId(String merchantId, String productId,int quantity);


}
