package com.example.MerchantService.services;

import com.example.MerchantService.dtos.MerchantDto;
import com.example.MerchantService.entity.Merchant;

public interface Merchantservice {

    /**
     * API to add merchant
     *
     * @param merchantDto dto with merchant details
     * @return
     */
    Merchant addMerchant(MerchantDto merchantDto);


    Merchant getMerchant(Integer id);

    Double getRating(Integer id);

    void setMerchantRating(Integer id,double rating);
}
