package com.example.MerchantService.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "productList")
@Getter
@Setter

public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
     String productId;
    Integer merchantId;
    Integer stock;
     Double price;
     Double Rating;


}
