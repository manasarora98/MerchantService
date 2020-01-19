package com.example.MerchantService.services.productlistservice;

import com.example.MerchantService.dtos.FinalDto;
import com.example.MerchantService.entity.ProductList;
import com.example.MerchantService.repositories.MerchantRepository;
import com.example.MerchantService.repositories.ProductListRepository;
import com.example.MerchantService.services.Merchantservice;
import com.example.MerchantService.services.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class productListServiceImpl implements ProductListService {
    @Autowired
    ProductListRepository productListRepository;
     @Autowired
    Merchantservice merchantservice;
    @Override
    public ProductList addProduct(ProductList productlist) {
        return productListRepository.save(productlist);
    }

    @Override
    public List<FinalDto> findByPid(String pid) {

    List<ProductList> list = productListRepository.findAllByProductId(pid);
  List<FinalDto> finallist=new ArrayList<>();
    System.out.println("error in getting product list");

        for(ProductList p: list){
        Integer merchantid=p.getMerchantId();
            Double merchantRating=0.0;
            FinalDto finalDto=new FinalDto();
            finalDto.setMerchantId(p.getMerchantId());
            finalDto.setPrice(p.getPrice());
            finalDto.setProductId(p.getProductId());
            finalDto.setRating(p.getRating());
            finalDto.setStock(p.getStock());
            try {
             merchantRating = merchantservice.getRating(merchantid);
             finalDto.setMerchantRating(merchantRating);
        }
        catch(Exception e){
            System.out.println("error in getting merchantrating");
        }
 finallist.add(finalDto);
        }

        return  finallist;
    }

    @Override
    @Transactional
    public void updateProduct(ProductList productList) {
        productListRepository.update(productList.getPrice(), productList.getStock(), productList.getMerchantId(), productList.getProductId());
    }


}
