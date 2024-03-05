package com.k35dl.g6.service.Product;

import java.util.List;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Product.Product;

public interface ProductService {
    public Product createProduct(Product product) throws Exception;

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Product product,Long productId) throws ProductException;

    public List<Product> getAllProducts();

    public Product findProductById(Long productId) throws ProductException;
}
