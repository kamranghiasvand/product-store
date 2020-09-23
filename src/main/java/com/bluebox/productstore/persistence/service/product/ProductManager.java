package com.bluebox.productstore.persistence.service.product;

import com.bluebox.productstore.rest.product.req.AddProductReq;
import com.bluebox.productstore.rest.product.req.EditProductNameReq;
import com.bluebox.productstore.rest.product.req.EditProductPriceReq;

public interface ProductManager {
    Long add(AddProductReq dto, String token) throws Exception;
    void remove(Long id, String token) throws Exception;
    void editPrice(EditProductPriceReq req, String token) throws Exception;
    void editName(EditProductNameReq req, String token) throws Exception;
}
