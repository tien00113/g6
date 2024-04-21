package com.k35dl.g6.service.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Category;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.models.Product.ProductImage;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.repository.CategoryRepository;
import com.k35dl.g6.repository.Product.ProductRepo;
import com.k35dl.g6.utils.ProductSpecification;
import com.k35dl.g6.utils.SearchCriteria;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product, List<SizeOption> sizeOptions, List<ToppingOption> toppingOptions,
            List<ProductImage> images) throws Exception {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new Exception("Category không tồn tại"));
            product.setCategory(category);
        } else {
            throw new Exception("Product phải có category");
        }

        for (SizeOption sizeOption : sizeOptions) {
            sizeOption.setProduct(product);
            product.getSizeOptions().add(sizeOption);
        }

        for (ToppingOption toppingOption : toppingOptions) {
            toppingOption.setProduct(product);
            product.getToppingOptions().add(toppingOption);
        }

        for (ProductImage image : images) {
            image.setProduct(product);
            product.getImage().add(image);
        }

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {

        Product product = findProductById(productId);

        productRepository.delete(product);
        return "Đã xóa thành công";
    }

    @Override
    public Product updateProduct(Product product) throws ProductException {
        Optional<Product> product1 = productRepository.findById(product.getId());

        if (product1.isEmpty()) {
            throw new ProductException("không tìm thấy sản phẩm có id " + product.getId());
        }

        Product oldProduct = product1.get();

        if (product.getName() != null) {
            oldProduct.setName(product.getName());
        }

        if (product.getImage() != null) {
            oldProduct.getImage().clear();
            for (ProductImage image : product.getImage()) {
                image.setProduct(oldProduct);
                oldProduct.getImage().add(image);
            }
        }
        
        if (product.getDescription() != null) {
            oldProduct.setDescription(product.getDescription());
        }

        if (product.getCategory() != null && !product.getCategory().equals(oldProduct.getCategory())) {

            oldProduct.setCategory(product.getCategory());

        }

        if (product.getPrice() != 0) {
            oldProduct.setPrice(product.getPrice());
        }

        if (product.getSalePrice() != product.getPrice()) {
            oldProduct.setSalePrice(product.getSalePrice());
        }

        if(product.getSizeOptions() !=null){
            oldProduct.getSizeOptions().clear();
            for (SizeOption sizeOption : product.getSizeOptions()) {
                sizeOption.setProduct(oldProduct);
                oldProduct.getSizeOptions().add(sizeOption);
            }
        }

        if (product.getToppingOptions() != null) {
            oldProduct.getToppingOptions().clear();
            for (ToppingOption toppingOption: product.getToppingOptions()) {
                toppingOption.setProduct(oldProduct);
                oldProduct.getToppingOptions().add(toppingOption);
            }
        }

        Product updatedProduct = productRepository.save(oldProduct);

        return updatedProduct;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isEmpty()) {
            throw new ProductException("Không tìm thấy sản phẩm có id" + productId);
        }

        return opt.get();
    }

    @Override
    public List<Product> findProductByListId(List<Long> productIds) throws ProductException {
        List<Product> products = new ArrayList<>();
        for (Long id : productIds) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductException("Product not found with id: " + id));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> findProductByCategory(Long categoryId) throws ProductException {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        if (products.isEmpty()) {
            throw new ProductException("Không tìm thấy sản phẩm nào với ID danh mục: " + categoryId);
        }
        return products;
    }

    @Override
    public List<Product> findProductByName(String productName) throws ProductException {

        return productRepository.findAll(new ProductSpecification(new SearchCriteria("name", ":", productName)));
    }

}
