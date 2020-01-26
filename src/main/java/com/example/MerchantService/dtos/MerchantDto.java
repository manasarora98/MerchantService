package com.example.MerchantService.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {
    Integer id;
    String name;
    String password;
    String email;
    Double rating;
    String imgurl;
}
