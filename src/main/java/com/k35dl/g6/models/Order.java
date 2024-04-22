package com.k35dl.g6.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @PrePersist
    public void generateOrderId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String datePart = now.format(formatter);
        String randomPart = new Random().ints(65, 91)
                .mapToObj(i -> (char) i)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        this.orderId = datePart + "H2T" + randomPart;
    }

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime deliveryDateTime;

    @ManyToOne
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int totalPrice;
    private int totalSalePrice;
    private String note;

    private LocalDateTime createAt;

    private LocalDateTime updateStatusAt;

    public enum OrderStatus {
        PLACED, // Đơn hàng đang chờ xử lý
        CONFIRMED, // Đơn hàng đã được xác nhận
        SHIPPED, // Đơn hàng đã được gửi đi
        DELIVERED, // Đơn hàng đã được giao
        CANCELLED // Đơn hàng đã hủy
    }
}
