// package com.k35dl.g6.models.Product;

// import java.util.List;

// import com.k35dl.g6.models.Cart;
// import com.k35dl.g6.models.Order;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
// import lombok.Data;

// @Entity
// @Data
// public class ProductDetail {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private Product product;

//     @ManyToOne
//     @JoinColumn(name="size_option_id")
//     private SizeOption selectedSize;

//     @ManyToMany
//     @JoinTable(name = "topping_option_id")
//     private List<ToppingOption> toppingOption;

//     @ManyToOne
//     @JoinColumn(name = "cart_id")
//     private Cart cart;

//     @ManyToMany
//     @JoinTable(name = "orders_id")
//     private List<Order> order;


//     private int quantity=1;
//     private String note;
//     private int price=0;
// }
