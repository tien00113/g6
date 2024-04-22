package com.k35dl.g6.service.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.OrderItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ReViewProduct;
import com.k35dl.g6.repository.Product.ProductRepo;
import com.k35dl.g6.repository.Product.ReViewProductRepository;

@Service
public class ReViewServiceImplement implements ReViewProductService {
    @Autowired
    private ReViewProductRepository reViewProductRepository;

    @Autowired
    private ProductRepo productRepository;

    @Override
    public List<ReViewProduct> createReview(User user, Order order, List<ReViewProduct> reViewProducts) {

        List<Product> products = order.getOrderItems().stream().map(OrderItem::getProduct).collect(Collectors.toList());

        for (int i = 0; i < products.size(); i++) {
            ReViewProduct reviewProduct = reViewProducts.get(i);
            Product product = products.get(i);

            reviewProduct.setProduct(product);
            reviewProduct.setUser(user);

            ReViewProduct savedReview = reViewProductRepository.save(reviewProduct);

            // product.getReViewProducts().add(savedReview);

            // productRepository.save(product);
            List<ReViewProduct> allReviews = reViewProductRepository.findByProduct(product);
            double totalRating = allReviews.stream().mapToInt(ReViewProduct::getRating).sum();
            double averageRating = totalRating / allReviews.size();

            // Round to one decimal place
            double roundedAverageRating = Math.round(averageRating * 10.0) / 10.0;

            // Update the average rating in the Product entity
            product.setAverageRating(roundedAverageRating);

            // Update only the average rating field in the database
            productRepository.updateAverageRating(product.getId(), roundedAverageRating);

            reViewProducts.set(i, savedReview);
        }

        return reViewProducts;
    }

}
