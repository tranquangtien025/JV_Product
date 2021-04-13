/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.repository;

import com.mycompany.jv_product.entities.OrderDetailEntity;
import com.mycompany.jv_product.entities.OrderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TIEN
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer>{
    
    List<OrderDetailEntity> findByOrder(OrderEntity orderEntity);
    List<OrderDetailEntity> findByOrderId(int orderId);
}
