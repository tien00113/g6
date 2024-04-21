package com.k35dl.g6.service.Product;

import java.util.List;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ProductImage;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;

public interface ProductService {
    public Product createProduct(Product product,List<SizeOption> sizeOptions, List<ToppingOption> toppingOptions, List<ProductImage> images) throws Exception;

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Product product) throws ProductException;

    public List<Product> getAllProducts();

    public Product findProductById(Long productId) throws ProductException;

    public List<Product> findProductByListId(List<Long> productIds) throws ProductException;

    public List<Product> findProductByCategory(Long categoryId) throws ProductException;

    public List<Product> findProductByName(String productName) throws ProductException;

}
