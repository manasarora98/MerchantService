package com.example.MerchantService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class productListDto {

    String  productId;
    Integer merchantId;
    Integer stock;
    Double price;
    Double rating;

}
