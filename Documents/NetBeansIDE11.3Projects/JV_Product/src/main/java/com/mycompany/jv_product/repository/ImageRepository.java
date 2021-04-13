/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.repository;

import com.mycompany.jv_product.entities.ImageEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TIEN
 */
@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer>{
    
    List<ImageEntity> findByNameLike (String imageName);
    List<ImageEntity> findByProductId(int id);
}
