package com.example.productstockservice.serviceimp;

import com.example.productstockservice.beans.ProductStockBean;
import com.example.productstockservice.entity.ProductStock;
import com.example.productstockservice.entity.ProductStockRepository;
import com.example.productstockservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImplimentation implements ProductService {

    private final ProductStockRepository productStockRepository;
    private final Environment environment;


    @Transactional
    @Override
    public ProductStockBean saveproductStock(ProductStockBean productStockBean) {
        ProductStock productStock = new ProductStock().builder()
                .productName(productStockBean.getProductName())
                .productAvailability(productStockBean.getProductAvailability())
                .productPrice(productStockBean.getProductPrice())
                .discountOffer(productStockBean.getDiscountOffer())
                .build();
        ProductStock savedProduct = productStockRepository.save(productStock);
        if (savedProduct != null) {
            productStockBean.setId(savedProduct.getId());
//            productStockBean.setPort(Integer.parseInt(environment.getProperty("local-server.port")));
        }
        return productStockBean;
    }

    @Transactional
    @Override
    public List<ProductStockBean> findAllStock() {
        return productStockRepository.findAll().stream().map(productStock ->
                        new ProductStockBean().builder()
                                .productPrice(productStock.getProductPrice())
                                .discountOffer(productStock.getDiscountOffer())
                                .productAvailability(productStock.getProductAvailability())
                                .productName(productStock.getProductName())
                                .id(productStock.getId())
//                        .port(Integer.parseInt(environment.getProperty("local-server.port")))
                                .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductStockBean updateProductStock(Long id, ProductStockBean productStockBean) {
        ProductStock productfromdatabase = productStockRepository.getById(id);
        productfromdatabase = new ProductStock().builder()
                .productName(productStockBean.getProductName())
                .productAvailability(productStockBean.getProductAvailability())
                .productPrice(productStockBean.getProductPrice())
                .discountOffer(productStockBean.getDiscountOffer())
                .build();
        ProductStock savedProduct = productStockRepository.save(productfromdatabase);
        if (savedProduct != null) {
            productStockBean.setId(savedProduct.getId());
//            productStockBean.setPort(Integer.parseInt(environment.getProperty("local-server.port")));

        }
        return productStockBean;

    }

    @Transactional
    @Override
    public void deleteProductStock(Long id) {
        ProductStock productfromdatabase = productStockRepository.getById(id);
        productStockRepository.delete(productfromdatabase);
    }

    @Override
    public ProductStockBean findByProductNameandProductAvailability(String productName, String productAvailability) {
        ProductStock productfromdatabase = productStockRepository.findByProductNameAndProductAvailability(
                productName, productAvailability);
        ProductStockBean productStockBean = new ProductStockBean().builder()
                .productName(productfromdatabase.getProductName())
                .productAvailability(productfromdatabase.getProductAvailability())
                .productPrice(productfromdatabase.getProductPrice())
                .discountOffer(productfromdatabase.getDiscountOffer())
                .id(productfromdatabase.getId())
//                .port(Integer.parseInt(environment.getProperty("local-server.port")))

                .build();

        return productStockBean;

    }
}
