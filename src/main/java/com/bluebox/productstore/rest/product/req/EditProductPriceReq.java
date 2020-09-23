package com.bluebox.productstore.rest.product.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProductPriceReq {
    private Long id;
    private double newPrice;


}
