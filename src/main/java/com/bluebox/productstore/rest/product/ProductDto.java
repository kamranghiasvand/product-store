package com.bluebox.productstore.rest.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private String company;
    private double price;
    private String token;
    private String field;
    private String newValue;


}
