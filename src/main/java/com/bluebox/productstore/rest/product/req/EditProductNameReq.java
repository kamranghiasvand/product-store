package com.bluebox.productstore.rest.product.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProductNameReq {
    private Long id;
    private String newName;
}
