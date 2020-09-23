package com.bluebox.productstore.rest.product;

import com.bluebox.productstore.persistence.service.product.ProductManager;
import com.bluebox.productstore.rest.product.req.AddProductReq;
import com.bluebox.productstore.rest.product.req.EditProductNameReq;
import com.bluebox.productstore.rest.product.req.EditProductPriceReq;
import com.bluebox.productstore.rest.product.req.RemoveProductReq;
import com.bluebox.productstore.rest.product.resp.AddProductResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
public class ProductController {

    private final ProductManager manager;

    @Autowired
    public ProductController(ProductManager manager) {
        this.manager = manager;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public AddProductResp addProduct(@RequestBody AddProductReq req, @RequestHeader("token") String token) throws Exception {
        return new AddProductResp(manager.add(req, token));
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public void removeProduct(@RequestBody RemoveProductReq req, @RequestHeader("token") String token) throws Exception {
        manager.remove(req.getId(), token);
    }


    @RequestMapping(path = "/edit/name", method = RequestMethod.PUT)
    public void editProductName(@RequestBody EditProductNameReq req, @RequestHeader("token") String token) throws Exception {
        manager.editName(req, token);
    }

    @RequestMapping(path = "/edit/price", method = RequestMethod.PUT)
    public void editProductPrice(@RequestBody EditProductPriceReq req, @RequestHeader("token") String token) throws Exception {
        manager.editPrice(req, token);
    }



}
