package com.example.productstockservice.services;

import com.example.productstockservice.beans.ProductStockBean;
import com.example.productstockservice.entity.ProductStock;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    //CRUD

    public ProductStockBean saveproductStock(ProductStockBean productStockBean);

    public List<ProductStockBean> findAllStock();

    public ProductStockBean updateProductStock(Long id, ProductStockBean productStockBean);

    public void deleteProductStock(Long id);

    public ProductStockBean findByProductNameandProductAvailability(String productName, String productAvailability);


}

