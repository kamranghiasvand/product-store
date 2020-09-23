package com.bluebox.productstore.persistence.service.purchase;

import com.bluebox.productstore.persistence.entity.CartEntity;
import com.bluebox.productstore.persistence.entity.ProductEntity;
import com.bluebox.productstore.persistence.repository.CartRepository;
import com.bluebox.productstore.persistence.repository.ProductRepository;
import com.bluebox.productstore.persistence.service.authentication.AuthenticationManager;
import com.bluebox.productstore.rest.purchase.ProductInCartReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PurchaseManagerImpl implements PurchaseManager{

    AuthenticationManager authenticationManager;
    ProductRepository productRepository;
    CartRepository cartRepository;

    @Autowired
    public PurchaseManagerImpl(AuthenticationManager authenticationManager, ProductRepository productRepository, CartRepository cartRepository) {
        this.authenticationManager = authenticationManager;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addProductToCart(Long id, String token) throws Exception {
        checkAddingToCartErrors(id, token);
        ProductEntity product = productRepository.findById(id).get();

        CartEntity cart = authenticationManager.getUserWithToken(token).getCart();

        cart.getProducts().add(product);

        cartRepository.save(cart);

    }

    private void checkAddingToCartErrors(Long id, String token) throws Exception {
        authenticationManager.checkToken(token);

        if (StringUtils.isEmpty(id)) {
            throw new Exception("null info");
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new Exception("wrong id");
        }

        if (!authenticationManager.getUserWithToken(token).getType().equals("customer")) {
            throw new Exception("wrong type");
        }

    }

}
