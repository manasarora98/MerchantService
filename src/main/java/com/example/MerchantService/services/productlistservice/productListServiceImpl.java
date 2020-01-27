package com.example.MerchantService.services.productlistservice;

import com.example.MerchantService.dtos.FinalDto;
import com.example.MerchantService.dtos.ProductIdAndNamesDTO;
import com.example.MerchantService.dtos.productListDto;
import com.example.MerchantService.entity.ProductList;
import com.example.MerchantService.repositories.MerchantRepository;
import com.example.MerchantService.repositories.ProductListRepository;
import com.example.MerchantService.services.Merchantservice;
import com.example.MerchantService.services.ProductFeign;
import com.example.MerchantService.services.ProductListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class productListServiceImpl implements ProductListService {
    @Autowired
    ProductListRepository productListRepository;
    @Autowired
    Merchantservice merchantservice;
    @Autowired
    ProductFeign productFeign;

  /*  @Autowired
    KafkaTemplate<String,String> kafkaTemplate;*/

    @Override
    public void addProduct(ProductList productlist) {
        productListRepository.save(productlist);
    }

    @Override
    public List<FinalDto> findByPid(String pid) {

        List<ProductList> list = productListRepository.findAllByProductId(pid);
        List<FinalDto> finallist = new ArrayList<>();

        for (ProductList p : list) {
            Integer merchantid = p.getMerchantId();
            Double merchantRating = 0.0;
            FinalDto finalDto = new FinalDto();
            finalDto.setMerchantId(p.getMerchantId());
            finalDto.setPrice(p.getPrice());
            finalDto.setProductId(p.getProductId());
            finalDto.setRating(p.getRating());
            finalDto.setStock(p.getStock());
                merchantRating = merchantservice.getRating(merchantid);
                finalDto.setMerchantRating(merchantRating);
            finallist.add(finalDto);
        }

        return finallist;
    }

    @Override
    @Transactional
    public void updateProduct(ProductList productList) {
        productListRepository.update(productList.getPrice(), productList.getStock(), productList.getMerchantId(), productList.getProductId());
    }

    @Override
    @Transactional
    public void updateStock(String productId,Integer merchantId,int stock) {
        int updatedStock = productListRepository.getStock(productId, merchantId);
        productListRepository.updateQuantity(updatedStock - stock, productId, Integer.valueOf(merchantId));
        if (updatedStock - stock == 0) {
            productListRepository.deleteProduct(productId, merchantId);

            boolean result = productListRepository.checkIfProductIsPresent(productId);

            if (result == false) {
             //   this.kafkaTemplate.send("productDelete", productId);
              //  productFeign.removeProduct(productId);


            }

        }
    }

    @Override
    public List<ProductIdAndNamesDTO> getProductNames(Integer merchantId){
        List<ProductList> productLists = productListRepository.findAllByMerchantId(merchantId);
        List<String> ids = new ArrayList<>();
        for (ProductList productList:productLists){
           productListDto productListDto1 = new productListDto();
           BeanUtils.copyProperties(productList,productListDto1);
           ids.add(productListDto1.getProductId());

        }
        List<ProductIdAndNamesDTO> productIdAndNamesDTOSlist = new ArrayList<>();
        List<String> names = productFeign.getNamesFeign(ids);
       for(int i=0;i<ids.size();i++){
           ProductIdAndNamesDTO productIdAndNamesDTOS=new ProductIdAndNamesDTO();
           productIdAndNamesDTOS.setProductId(ids.get(i));
//           productIdAndNamesDTOS.setProductName((names.get(i)));
           productIdAndNamesDTOSlist.add(productIdAndNamesDTOS);

       }
       for(int j=0;j<names.size();j++){
           productIdAndNamesDTOSlist.get(j).setProductName(names.get(j));
       }

       return productIdAndNamesDTOSlist;
    }


    @Override
    public boolean checkStockFeign(String productId, Integer merchantId, Integer quantity) {
        int totalStock = productListRepository.getStock(productId,merchantId);
        if(totalStock >= quantity) return true;
        return false;
    }

    @Override
    @Transactional
    public void setProductRating(String productId, double rating) {
        productListRepository.setProductRating(productId,rating);
    }

    @Override
    public List<ProductList> getProductListOfMerchant(Integer merchantId) {
       return  productListRepository.findAllByMerchantId(merchantId);
    }
}

