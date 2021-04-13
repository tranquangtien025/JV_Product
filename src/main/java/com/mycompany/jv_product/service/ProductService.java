/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.service;

import com.mycompany.jv_product.entities.ProductEntity;
import com.mycompany.jv_product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TIEN
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<ProductEntity> getProducts () {
        return (List<ProductEntity>) productRepository.findAll();
    }
    
    public void saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }
    
    public ProductEntity getProductById(int id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return new ProductEntity();
        }
    }
    
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
