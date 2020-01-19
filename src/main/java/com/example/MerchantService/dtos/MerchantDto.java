package com.example.MerchantService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantDto {
    Integer id;
    String name;
    String password;
    String email;
    Double rating;
    String imgurl;
}
