package com.example.MerchantService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalDto {
    String  productId;
    Integer merchantId;
    Integer stock;
    Double price;
    Double rating;
    Double merchantRating;
}
