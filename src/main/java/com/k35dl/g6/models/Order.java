package com.k35dl.g6.models;

import java.time.LocalDateTime;
import java.util.List;

import com.k35dl.g6.models.Product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Product> products;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    private int totalPrice;

    public enum OrderStatus {
        PENDING, // Đơn hàng đang chờ xử lý
        PROCESSING, // Đơn hàng đang được xử lý
        SHIPPED, // Đơn hàng đã được gửi đi
        DELIVERED, // Đơn hàng đã được giao
        CANCELLED // Đơn hàng đã hủy
    }
}
