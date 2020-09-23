package com.bluebox.productstore.persistence.service.purchase;

import com.bluebox.productstore.rest.purchase.ProductInCartReq;

public interface PurchaseManager {
    void addProductToCart(Long id, String token) throws Exception;
}
