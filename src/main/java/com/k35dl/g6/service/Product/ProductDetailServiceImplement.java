// package com.k35dl.g6.service.Product;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.k35dl.g6.models.Product.Product;
// import com.k35dl.g6.models.Product.ProductDetail;
// import com.k35dl.g6.models.Product.SizeOption;
// import com.k35dl.g6.models.Product.ToppingOption;
// import com.k35dl.g6.repository.Product.ProductDetailRepository;

// @Service
// public class ProductDetailServiceImplement implements ProductDetailService {

//     @Autowired
//     private ProductDetailRepository productDetailRepository;

//     @Override
//     public ProductDetail addProductDetail(Product product, SizeOption sizeOption, List<ToppingOption> toppingOptions, int quantity, String note) {
//         if (product == null) {
//             throw new IllegalArgumentException("Product null");
//         }

//         if (sizeOption == null) {
//             throw new IllegalArgumentException("Hãy chọn size");
//         }

//         if (toppingOptions != null && toppingOptions.size() > 2) {
//             throw new IllegalArgumentException("Chỉ được chọn tối đa 2 topping");
//         }

//         if (quantity<1) {
//             throw new IllegalArgumentException("Lỗi số lượng");
//         }

//         ProductDetail newProductDetail = new ProductDetail();
        
//         newProductDetail.setProduct(product);
//         newProductDetail.setSelectedSize(sizeOption);
//         newProductDetail.setToppingOption(toppingOptions);
//         newProductDetail.setQuantity(quantity);
//         newProductDetail.setNote(note);

//         int price = calculatePrice(sizeOption, toppingOptions);
//         int totalPrice = price*quantity;
//         newProductDetail.setPrice(totalPrice);

//         return productDetailRepository.save(newProductDetail);
//     }
//     //tính tổng tiền
//     private int calculatePrice(SizeOption sizeOption, List<ToppingOption> toppingOptions){
//         int price = sizeOption.getPrice();
//         for (ToppingOption topping: toppingOptions) {
//             price += topping.getPrice();
//         }

//         return price;
//     }

//     @Override
//     public ProductDetail updateProductDetail(ProductDetail productDetail, Long productDetailId) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'updateProductDetail'");
//     }

//     @Override
//     public ProductDetail findProductDetailById(Long productDetailId) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findProductDetailById'");
//     }

// }
