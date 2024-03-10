package com.k35dl.g6.service.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.ProductException;
import com.k35dl.g6.models.Product.Product;
import com.k35dl.g6.repository.Product.ProductRepo;

@Service
public class ProductServiceImplement implements ProductService{

    @Autowired
    private ProductRepo productRepository;


    @Override
    public Product createProduct(Product product) throws Exception{

        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setImage(product.getImage());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setSalePrice(product.getSalePrice());
        newProduct.setSizeOptions(product.getSizeOptions());
        newProduct.setToppingOptions(product.getToppingOptions());
        newProduct.setCategory(product.getCategory());

        return productRepository.save(newProduct);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {

        Product product = findProductById(productId);
        
        //kiểm tra xem user này là admin hay không (ROLE_ADMIN) rồi xóa

        productRepository.delete(product);
        return "Đã xóa thành công";
    }

    @Override
    public Product updateProduct(Product product, Long productId) throws ProductException {
        Optional<Product> product1 = productRepository.findById(productId);
        
        if (product1.isEmpty()) {
            throw new ProductException("không tìm thấy sản phẩm có id "+productId);
        }

        Product oldProduct = product1.get();

        if(product.getName() != null){
            oldProduct.setName(product.getName());
        }

        if(product.getImage() != null){
            oldProduct.setImage(product.getImage());
        }
        if(product.getDescription()!= null){
            oldProduct.setDescription(product.getDescription());
        }

        if(product.getCategory()!= null){
            oldProduct.setCategory(product.getCategory());
        }

        if(product.getPrice() != 0){
            oldProduct.setPrice(product.getPrice());
        }

        if(product.getSalePrice()!=product.getPrice()){
            oldProduct.setSalePrice(product.getSalePrice());
        }

        if(product.getToppingOptions()!= null){
            oldProduct.setToppingOptions(product.getToppingOptions());
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
    
}
