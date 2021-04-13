/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.service;

import com.mycompany.jv_product.entities.ImageEntity;
import com.mycompany.jv_product.entities.ProductEntity;
import com.mycompany.jv_product.repository.ImageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TIEN
 */
@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    public List<ImageEntity> getImages() {
        return (List<ImageEntity>) imageRepository.findAll();
    }
    
    public void saveImage(ImageEntity imageEntity) {
        imageRepository.save(imageEntity);
    }
    
    public ImageEntity getImagetById(int id) {
        Optional<ImageEntity> optional = imageRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return new ImageEntity();
        }
    }
    
    public void deleteImage(int id) {
        imageRepository.deleteById(id);
    }
    
    public void deleteAllImageWithSameProductId(int id) {
        imageRepository.deleteAll(imageRepository.findByProductId(id));
    }
    
    public List<ImageEntity> getImagesOfProductId(int id) {
        return imageRepository.findByProductId(id);
    }
    
    public List<ImageEntity> getListImageNameLike(String name){
        return imageRepository.findByNameLike(name);
    }
}
