/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.service;

import com.mycompany.jv_product.entities.OrderEntity;
import com.mycompany.jv_product.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TIEN
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<OrderEntity> getOrders() {
        return (List<OrderEntity>) orderRepository.findAll();
    }
    
    public void saveOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }
    
    public OrderEntity getOrderById(int id) {
        Optional<OrderEntity> optional = orderRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return new OrderEntity();
        }
    }
}
