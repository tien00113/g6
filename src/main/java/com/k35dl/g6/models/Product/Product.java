package com.k35dl.g6.models.Product;

import java.util.ArrayList;
import java.util.List;

import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.Category;
import com.k35dl.g6.models.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;
    private int price = 0;
    private int salePrice = price;
    // private int quantity;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReViewProduct> reViewProducts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_topping", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name="topping_option_id"))
    private List<ToppingOption> toppingOptions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name ="size_option_id"))
    private List<SizeOption> sizeOptions = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    // @ManyToOne
    // @JoinColumn(name = "cart_id")
    // private Cart cart;

    // @ManyToMany
    // @JoinTable(name = "orders", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "orders_id"))
    // private List<Order> orders = new ArrayList<>();

}
