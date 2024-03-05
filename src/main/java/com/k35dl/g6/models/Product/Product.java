package com.k35dl.g6.models.Product;

import java.util.List;

import com.k35dl.g6.models.Category;

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
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReViewProduct> reViewProducts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_topping", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name="topping_option_id"))
    private List<ToppingOption> toppingOptions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name ="size_option_id"))
    private List<SizeOption> sizeOptions;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
