package com.bluebox.productstore.rest.product.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductReq {
    private String name;
    private String company;
    private double price;
}
