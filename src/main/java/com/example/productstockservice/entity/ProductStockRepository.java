package com.example.productstockservice.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

    ProductStock findByProductNameAndProductAvailability(String name, String availability);
}
