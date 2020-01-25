package com.example.MerchantService.controller;

import com.example.MerchantService.config.JwtGenerator;
import com.example.MerchantService.dtos.AccessTokenDto;
import com.example.MerchantService.dtos.MerchantDto;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.services.Merchantservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    Merchantservice merchantservice;

    @Autowired
    JwtGenerator jwtGenerator;

    @PostMapping("/register")
    public void register(@RequestBody MerchantDto merchantDto)
    {
        Merchant merchantEntity=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchantEntity);
        merchantservice.save(merchantEntity);

    }

    @PostMapping("/login")
    public AccessTokenDto login(@RequestBody MerchantDto merchantDto){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        Merchant merchantEntity=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchantEntity);
        Merchant merchantExist = merchantservice.findByEmail(merchantEntity);
        if(merchantExist != null){
            String accessToken =  jwtGenerator.generateToken(merchantEntity);
            accessTokenDto.setAccessToken(accessToken);
            accessTokenDto.setUserId(merchantExist.getId());
            return accessTokenDto;
        }else{
            return new AccessTokenDto();
        }

    }

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
