package com.example.MerchantService.services.merchantservice;

import com.example.MerchantService.dtos.MerchantDto;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.repositories.MerchantRepository;
import com.example.MerchantService.services.Merchantservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MerchantserviceImpl implements Merchantservice {

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant addMerchant(MerchantDto merchantDto){
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
       return merchantRepository.save(merchant);

    }
    @Override
   public  Merchant getMerchant(Integer id){
        Optional<Merchant> merchant= merchantRepository.findById(id);

        return merchant.get();

    }

    @Override
    public  Double getRating(Integer id){
        Optional<Merchant> merchant= merchantRepository.findById(id);
        Merchant merchant1= merchant.get();
        return merchant1.getRating();
    }

    @Transactional
    @Override
    public void setMerchantRating(Integer id, double rating) {
        merchantRepository.setMerchantRating(id,rating);
    }

    @Override
    public void save(Merchant merchantEntity) {
        merchantRepository.save(merchantEntity);
    }
    @Override
    public Merchant findByEmail(Merchant merchantEntity) {
        Merchant merchant =  merchantRepository.findByEmail(merchantEntity.getEmail());
        String pass1 = String.valueOf(merchant.getPassword().hashCode());
        String pass2 = String.valueOf(merchantEntity.getPassword().hashCode());
        if(pass1.equals(pass2) && merchant != null){
            return merchant;
        }
        return  merchant;
    }
}
