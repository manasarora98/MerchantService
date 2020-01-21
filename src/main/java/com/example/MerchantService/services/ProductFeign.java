package com.example.MerchantService.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "productFeign" , url = "http://localhost:8086/")
public interface ProductFeign {
    @RequestMapping(method = RequestMethod.GET , value = "product/getNamesFeign/{productId}")
    String getNamesFeign(@PathVariable("productId") String productId);
}
