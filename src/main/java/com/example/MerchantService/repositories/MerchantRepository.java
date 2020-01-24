package com.example.MerchantService.repositories;

import com.example.MerchantService.entity.Merchant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Integer> {

    @Modifying
    @Query(value = "Update merchant set rating=?2 where id=?1",nativeQuery = true)
    void setMerchantRating(Integer id,double rating);
}
