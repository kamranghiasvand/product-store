package com.bluebox.productstore.persistence.service.product;

import com.bluebox.productstore.rest.product.ProductDto;

public interface ProductManager {
    String add(ProductDto dto) throws Exception;
    void edit(ProductDto id) throws Exception;
    void remove(long id, String token) throws Exception;


}
