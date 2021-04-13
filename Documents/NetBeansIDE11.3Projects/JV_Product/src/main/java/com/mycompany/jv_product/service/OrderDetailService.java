/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.service;

import com.mycompany.jv_product.entities.OrderDetailEntity;
import com.mycompany.jv_product.entities.OrderEntity;
import com.mycompany.jv_product.repository.OrderDetailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TIEN
 */
@Service
public class OrderDetailService {
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    public List<OrderDetailEntity> findOrderDetailsByOrder(OrderEntity orderEntity) {
        return (List<OrderDetailEntity>) orderDetailRepository.findByOrder(orderEntity);
    }
    
    public List<OrderDetailEntity> findOrderDetailsByOrderId(int orderId) {
        return (List<OrderDetailEntity>) orderDetailRepository.findByOrderId(orderId);
    }
    
    public void saveOrderDetail(OrderDetailEntity orderDetailEntity) {
        orderDetailRepository.save(orderDetailEntity);
    }
    
    public OrderDetailEntity getOrderDetailById(int id) {
        Optional<OrderDetailEntity> optional = orderDetailRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return new OrderDetailEntity();
        }
    }
}
