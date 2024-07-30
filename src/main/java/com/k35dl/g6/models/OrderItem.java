package com.k35dl.g6.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int price;
    private int priceSale;
    private int quantity;

    private Long userId;
    // private LocalDateTime deiveryDateTime;

    @ManyToOne
    private SizeOption sizeOption;
    @ManyToOne
    private ToppingOption toppingOption;

}
