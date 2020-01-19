package com.example.MerchantService.repositories;

import com.example.MerchantService.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Integer> {

}
