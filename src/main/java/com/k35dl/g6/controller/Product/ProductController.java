package com.k35dl.g6.controller.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.Product.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Exception {

        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId)
            throws Exception {

        Product updatedProduct = productService.updateProduct(product, productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        String message = productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(allProducts,HttpStatus.ACCEPTED);
    }
}
