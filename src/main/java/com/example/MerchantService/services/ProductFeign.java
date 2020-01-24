package com.example.MerchantService.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value= "productFeign" , url = "http://localhost:8086/")
public interface ProductFeign {
    @RequestMapping(method = RequestMethod.POST , value = "product/getNamesFeign")
    List<String> getNamesFeign(@RequestBody List<String> productIds);
}
