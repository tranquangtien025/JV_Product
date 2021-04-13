/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.service;

import com.mycompany.jv_product.entities.CategoryEntity;
import com.mycompany.jv_product.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TIEN
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryEntity> getCategories () {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
}
