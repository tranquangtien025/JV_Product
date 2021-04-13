/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv_product.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author TIEN
 */
@Entity
@Table(name = "order_list")
public class OrderEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private Date orderDate;
    
    @Column(name = "total_price", nullable = false)
    @DecimalMin("0.0")
    private double totalPrice;
    
    @Column(name = "customer_name", nullable = false,  length = 100)
    @NotBlank(message = "This field is required")
    @Size(min = 3, max = 100)
    private String customerName;
    
    @Column(name = "customer_phone", nullable = false)
    @NotBlank(message = "This field is required")
    @Pattern(regexp="(^$|[0-9]{10})", message = "For Ex: 0123456789")
    private String customerPhone;
    
    @Column(name = "customer_address", nullable = false)
    @NotBlank(message = "This field is required")
    private String customerAddress;
    
    @Column(name = "customer_email")
//    @Pattern(regexp ="^(.+)@(.+)$", message = "For Ex: abc@gmail.com")
//    @Email(message = "For Ex: abc@gmail.com")
    private String customerEmail;
    
    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> orderDetails;

    public OrderEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Set<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }   
    
}
