package com.example.MerchantService.controller;

import com.example.MerchantService.dtos.FinalDto;
import com.example.MerchantService.dtos.ProductIdAndNamesDTO;
import com.example.MerchantService.dtos.productListDto;
import com.example.MerchantService.entity.ProductList;
import com.example.MerchantService.services.ProductListService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/productList")
public class ProductController {
    @Autowired
    ProductListService productListService;

    @PostMapping("/addProduct")
    public ResponseEntity<Integer> addProduct(@RequestBody productListDto productListDto) {
        ProductList productList = new ProductList();
        BeanUtils.copyProperties(productListDto, productList);
        productListService.addProduct(productList);
        return new ResponseEntity<>(productList.getId(), HttpStatus.CREATED);

    }

    @PostMapping("/updateProduct")
    public void updateProduct(@RequestBody productListDto productListDto) {
        ProductList productList = new ProductList();
        BeanUtils.copyProperties(productListDto, productList);
        productListService.updateProduct(productList);

    }
    ///Get the merchants of a particular productId
    @GetMapping("/getProductList/{id}")
    public List<FinalDto> getProductList(@PathVariable("id") String id) {

        return productListService.findByPid(id);

    }
    ///After order is placed
    @PostMapping("/updateStock/{productId}/{merchantId}/{quantity}")
    public void updateStock(@PathVariable("productId") String productId,@PathVariable("merchantId") Integer merchantId,@PathVariable("quantity") int quantity){
        productListService.updateStock(productId,merchantId,quantity);
    }

    @GetMapping("/getProductNames/{merchantId}")
    public List<ProductIdAndNamesDTO> getMerchantNames(@PathVariable("merchantId") Integer merchantId){

       return productListService.getProductNames(merchantId);
    }

//    @PostMapping("/setProductRating/{userId}/{productId}/{merchantId}/{rating}")
//    public void setProductRating(@PathVariable("userId") String userId,@PathVariable("productId") String productId,@PathVariable("merchantId") String merchantId,@PathVariable("rating") int rating){
//        productListService.setProductRating(userId,productId,merchantId,rating);
//    }

    @GetMapping("/checkStock/{productId}/{merchantId}/{quantity}")
    public boolean checkStockFeign(@PathVariable("productId") String productId,@PathVariable("merchantId") Integer merchantId,@PathVariable("quantity") Integer quantity ){
        return productListService.checkStockFeign(productId,merchantId,quantity);

    }




}


