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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ProductImage;
import com.k35dl.g6.models.Product.ReViewProduct;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.repository.Product.ProductRepo;
import com.k35dl.g6.repository.Product.ReViewProductRepository;
import com.k35dl.g6.request.CreateProductRequest;
import com.k35dl.g6.request.ReviewProductRequest;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.UserSerVice;
import com.k35dl.g6.service.Product.ProductService;
import com.k35dl.g6.service.Product.ReViewProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ReViewProductService reViewProductService;

    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private ReViewProductRepository reViewProductRepository;

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

    @PutMapping("/admin/products/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
            throws Exception {

        Product updatedProduct = productService.updateProduct(product);

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
    @GetMapping("allproduct/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            List<Product> products = productService.findProductByCategory(categoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allproduct/search")
    public ResponseEntity<List<Product>> getProductByName (@RequestParam("query") String productName) throws ProductException{
        List<Product> products = productService.findProductByName(productName);

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PutMapping("/api/product/review")
    public ResponseEntity<List<ReViewProduct>> reviewProduct (@RequestHeader("Authorization") String jwt ,@RequestBody ReviewProductRequest request){
        User user = userSerVice.findUserByJwt(jwt);
        List<ReViewProduct> reViewProducts = reViewProductService.createReview(user,request.getOrder(), request.getReviewProducts());

        return new ResponseEntity<List<ReViewProduct>>(reViewProducts, HttpStatus.CREATED);
    }

    @GetMapping("/allproduct/helloworld")
    public String test(){
        return "new code and new image";
    }

}
