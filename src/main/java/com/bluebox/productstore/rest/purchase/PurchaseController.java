package com.bluebox.productstore.rest.purchase;

import com.bluebox.productstore.persistence.service.purchase.PurchaseManager;
import com.bluebox.productstore.rest.product.req.EditProductPriceReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/purchase", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
public class PurchaseController {
    PurchaseManager purchaseManager;

    @Autowired
    public PurchaseController(PurchaseManager purchaseManager) {
        this.purchaseManager = purchaseManager;
    }

    @RequestMapping(path = "/add-to-cart", method = RequestMethod.POST)
    public void addToCart(@RequestBody ProductInCartReq req, @RequestHeader("token") String token) throws Exception {
        purchaseManager.addProductToCart(req.getId(), token);
    }

}
