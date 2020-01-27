package com.example.MerchantService.services;

import com.example.MerchantService.dtos.FinalDto;
import com.example.MerchantService.dtos.ProductIdAndNamesDTO;
import com.example.MerchantService.entity.ProductList;

import java.util.List;


public interface ProductListService {
    List<FinalDto> findByPid(String pid);

    void addProduct(ProductList productlist);

    void updateProduct(ProductList productList);

    void updateStock(String productId,Integer merchantId,int quantity);

    List<ProductIdAndNamesDTO> getProductNames(Integer merchantId);

    boolean checkStockFeign(String productId,Integer merchantId,Integer quantity);


    void setProductRating(String productId, double rating);

    List<ProductList> getProductListOfMerchant(Integer merchantId);
}
