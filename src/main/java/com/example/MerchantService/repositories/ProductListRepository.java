package com.example.MerchantService.repositories;

import com.example.MerchantService.entity.ProductList;
import org.apache.kafka.common.protocol.types.Field;
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

    @Modifying
    @Query(value = "Update product_list set stock =?1 where product_id =?2 and merchant_id=?3",nativeQuery=true)
    void updateQuantity(int stock, String productId,Integer merchantId);

   //  List<ProductList> findByMerchantIdAndProductId(String merchantId, String productId,int quantity);

   /* @Query(value = "Update product_list set rating =?3 where product_id =?2 and merchant_id=?3",nativeQuery=true)
    void setProductRating(String productId, String merchantId,int rating);*/

   List<ProductList> findAllByMerchantId(Integer id);

   @Query(value = "Select stock from product_list where product_id=?1 and merchant_id=?2", nativeQuery = true)
   int getStock(String productId,Integer merchantId);

   @Modifying
   @Query(value = "Delete from product_list where product_id=?1 and merchant_id=?2",nativeQuery = true)
    void deleteProduct(String productId,Integer merchantId);


    @Query(value = "Select (case when count(product_id)>0 then true else false end) from Product_list where product_id=?1",nativeQuery = true)
    boolean checkIfProductIsPresent(String productId);








}
