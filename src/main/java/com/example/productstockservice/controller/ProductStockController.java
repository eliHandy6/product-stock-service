package com.example.productstockservice.controller;

import com.example.productstockservice.beans.ProductStockBean;
import com.example.productstockservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/apis/v1/products")
public class ProductStockController {

    private final ProductService productService;

    //CRUD

    @PostMapping
    public ResponseEntity<ProductStockBean> saveProductStock(@RequestBody ProductStockBean productStockBean) {
        return new ResponseEntity<>(
                productService.saveproductStock(productStockBean), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProductStockBean>> getAllProducts() {
        return new ResponseEntity<>(
                productService.findAllStock(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductStockBean> updateProduct(@PathVariable Long id, ProductStockBean productStockBean) {
        return new ResponseEntity<>(
                productService.updateProductStock(id, productStockBean), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductStock(id);

    }

    @GetMapping("/search")
    public ResponseEntity<ProductStockBean> searchForProductInStock(@RequestParam String name, @RequestParam String availability) {
        return new ResponseEntity<>(
                productService.findByProductNameandProductAvailability(name, availability), HttpStatus.OK);

    }


}
