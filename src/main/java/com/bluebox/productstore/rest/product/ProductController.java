package com.bluebox.productstore.rest.product;

import com.bluebox.productstore.persistence.service.authentication.AuthenticationManager;
import com.bluebox.productstore.persistence.service.product.ProductManager;
import com.bluebox.productstore.rest.authenticate.LoginResp;
import com.bluebox.productstore.rest.authenticate.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductManager manager;

    @Autowired
    public ProductController(ProductManager manager) {
        this.manager = manager;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddProductResp addProduct(@RequestBody ProductDto dto) throws Exception {
        final String id = manager.add(dto);
        return new AddProductResp(id);
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editProduct(@RequestBody ProductDto dto) throws Exception {
        manager.edit(dto);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeProduct(@RequestBody ProductDto dto) throws Exception {
        manager.remove(dto.getId(), dto.getToken());
    }

}
