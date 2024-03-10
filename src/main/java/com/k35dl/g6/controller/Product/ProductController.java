package com.k35dl.g6.controller.Product;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Category;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.request.CreateProductRequest;
import com.k35dl.g6.response.ApiResponse;
import com.k35dl.g6.service.CategoryService;
import com.k35dl.g6.service.Product.ProductService;
import com.k35dl.g6.service.Product.SizeOptionService;
import com.k35dl.g6.service.Product.ToppingOptionService;

@RestController
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired 
    private SizeOptionService sizeOptionService;

    @Autowired
    private ToppingOptionService toppingOptionService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest)
            throws Exception {

        Product product = createProductRequest.getProduct();
        List<Long> sizeOptionIds = createProductRequest.getSizeOptionIds();
        List<Long> toppingOptionIds = createProductRequest.getToppingOptionIds();

        //creat category////////////////////////////////////////////////////////////////////////////////
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryService.findCategoryById(product.getCategory().getId());

            if (category != null) {
                product.setCategory(category);
            } else {
                throw new Exception("Category không tồn tại");
            }
        } else {
            throw new Exception("Product phải có category");
        }

        //create sizeoption/////////////////////////////////////////////////////////////////////////

        List<SizeOption> sizeOptions = new ArrayList<>();
        for (Long id : sizeOptionIds) {
            SizeOption sizeOption = sizeOptionService.findSizeOptionById(id);
            if (sizeOption != null) {
                sizeOptions.add(sizeOption);
            } else {
                throw new Exception("Size với ID " + id + " không tồn tại");
            }
        }
        product.setSizeOptions(sizeOptions);

        //create toppingoption ////////////////////////////////////////////////////////////////////

        List<ToppingOption> toppingOptions = new ArrayList<>();

        for(Long id : toppingOptionIds){
            ToppingOption toppingOption = toppingOptionService.findToppingOptionById(id);
            if (toppingOption != null) {
                toppingOptions.add(toppingOption);
            } else {
                throw new Exception("Topping với ID " + id + " không tồn tại");
            }
        }
        product.setToppingOptions(toppingOptions);

        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.ACCEPTED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId)
            throws Exception {

        Product updatedProduct = productService.updateProduct(product, productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/products/{productId}")
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
