package com.example.MerchantService.services;

import com.example.MerchantService.dtos.MerchantDto;
import com.example.MerchantService.entity.Merchant;

public interface Merchantservice {
    Merchant addMerchant(MerchantDto merchantDto);
    Merchant getMerchant(Integer id);
    Double getRating(Integer id );
}
