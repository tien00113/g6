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
import com.k35dl.g6.models.Product.ProductImage;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.repository.Product.ProductRepo;
import com.k35dl.g6.request.CreateProductRequest;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.Product.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/admin/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest)
            throws Exception {

        Product product = createProductRequest.getProduct();
        List<SizeOption> sizeOptions = createProductRequest.getSizeOptions();
        List<ToppingOption> toppingOptions = createProductRequest.getToppingOptions();
        List<ProductImage> images = createProductRequest.getProductImages();
        
        Product createdProduct = productService.createProduct(product,sizeOptions,toppingOptions,images);

        productRepo.save(createdProduct);

        return new ResponseEntity<>(createdProduct, HttpStatus.ACCEPTED);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId)
            throws Exception {

        Product updatedProduct = productService.updateProduct(product, productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        String message = productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.ACCEPTED);
    }
}
