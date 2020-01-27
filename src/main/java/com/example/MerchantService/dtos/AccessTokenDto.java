package com.example.MerchantService.dtos;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AccessTokenDto {
    int userId;
    String accessToken;
    String email;
}