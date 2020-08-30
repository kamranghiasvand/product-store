package com.bluebox.productstore.persistence.service.product;

import com.bluebox.productstore.persistence.entity.ProductEntity;
import com.bluebox.productstore.persistence.repository.ProductRepository;
import com.bluebox.productstore.persistence.service.authentication.AuthenticationManager;
import com.bluebox.productstore.rest.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ProductManagerImpl implements ProductManager{

    private final ProductRepository productRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ProductManagerImpl(ProductRepository productRepository, AuthenticationManager authenticationManager) {
        this.productRepository = productRepository;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public String add(ProductDto dto) throws Exception {
        checkToken(dto.getToken());

        if (StringUtils.isEmpty(dto.getCompany())
            || StringUtils.isEmpty(dto.getName())
            || StringUtils.isEmpty(dto.getPrice())) {
            throw new Exception("null info");
        }


        ProductEntity productEntity = new ProductEntity(dto.getName(), dto.getPrice(), dto.getCompany(),
                authenticationManager.findUsernameWithToken(dto.getToken()));
        productRepository.save(productEntity);

        return "" + productEntity.getId();

    }

    @Override
    public void edit(ProductDto dto) throws Exception {
        checkToken(dto.getToken());

        if (StringUtils.isEmpty(dto.getId())
                || StringUtils.isEmpty(dto.getField())
                || StringUtils.isEmpty(dto.getNewValue())) {
            throw new Exception("null info");
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(dto.getId());

        if (optionalProduct.isPresent()) {
            String seller = authenticationManager.findUsernameWithToken(dto.getToken());
            if (!seller.equals(optionalProduct.get().getSeller())) {
                throw new Exception("wrong seller");
            }
        } else {
            throw new Exception("wrong product id");
        }

        optionalProduct.get().setNewValueForField(dto.getField(), dto.getNewValue());
    }

    @Override
    public void remove(long id, String token) throws Exception {
        checkToken(token);

        if (StringUtils.isEmpty(id))
            throw new Exception("null info");

        String seller = authenticationManager.findUsernameWithToken(token);
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            String realSeller = optionalProduct.get().getSeller();
            if (!realSeller.equals(seller)) {
                throw new Exception("wrong seller");
            }
        } else {
            throw new Exception("wrong product id");
        }

        productRepository.deleteById(id);
    }

    private void checkToken(String token) throws Exception {
        if (StringUtils.isEmpty(token))
            throw new Exception("null info");

        if (!authenticationManager.isTokenValid(token))
            throw new Exception("invalid token");

        if (authenticationManager.isTokenExpired(token))
            throw new Exception("expired token");
    }

}
