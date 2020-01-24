package com.example.MerchantService.controller;

import com.example.MerchantService.dtos.MerchantDto;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.services.Merchantservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    Merchantservice merchantservice;

    @PostMapping(value = "/addMerchant")
    public ResponseEntity<Integer> addMerchant (@RequestBody  MerchantDto merchantDto){
        Merchant merchant=merchantservice.addMerchant(merchantDto);
        return new ResponseEntity<Integer>(merchant.getId(),HttpStatus.CREATED);

    }
    @GetMapping(value = "/getMerchant/{id}")
    public Merchant getMerchant(@PathVariable("id") Integer id){
        return merchantservice.getMerchant(id);
    }


    @GetMapping(value = "getMerchantRating/{id}")
    public Double getMerchantRating(@PathVariable("id") Integer id){
        return  merchantservice.getRating(id);
    }

    @PostMapping(value = "setMerchantRating/{id}/{rating}")
    public boolean setMerchantRating(@PathVariable("id") Integer id,@PathVariable("rating") double rating){
        merchantservice.setMerchantRating(id,rating);
        return true;
    }

}
