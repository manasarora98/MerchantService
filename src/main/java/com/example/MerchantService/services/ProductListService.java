package com.example.MerchantService.services;

import com.example.MerchantService.dtos.FinalDto;
import com.example.MerchantService.entity.ProductList;

import java.util.List;


public interface ProductListService {
    List<FinalDto> findByPid(String pid);

    void addProduct(ProductList productlist);

    void updateProduct(ProductList productList);

    void updateStock(String productId,String merchantId,int quantity);

    String getProductNames(String merchantId);


}
