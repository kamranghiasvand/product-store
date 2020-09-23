package com.bluebox.productstore.persistence.service.product;

import com.bluebox.productstore.persistence.entity.CompanyEntity;
import com.bluebox.productstore.persistence.entity.ProductEntity;
import com.bluebox.productstore.persistence.entity.UserEntity;
import com.bluebox.productstore.persistence.repository.CompanyRepository;
import com.bluebox.productstore.persistence.repository.ProductRepository;
import com.bluebox.productstore.persistence.service.authentication.AuthenticationManager;
import com.bluebox.productstore.rest.product.req.AddProductReq;
import com.bluebox.productstore.rest.product.req.EditProductNameReq;
import com.bluebox.productstore.rest.product.req.EditProductPriceReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ProductManagerImpl implements ProductManager {

    private final ProductRepository productRepository;
    private final AuthenticationManager authenticationManager;
    private final CompanyRepository companyRepository;

    @Autowired
    public ProductManagerImpl(ProductRepository productRepository, AuthenticationManager authenticationManager
            , CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.authenticationManager = authenticationManager;
        this.companyRepository = companyRepository;
    }



    @Override
    public Long add(AddProductReq req, String token) throws Exception {
        checkAddErrors(req, token);
        ProductEntity productEntity = new ProductEntity(req.getName(), req.getPrice(),
                authenticationManager.getUserWithToken(token), findCompany(req.getCompany()));

        productRepository.save(productEntity);

        return productEntity.getId();

    }

    private CompanyEntity findCompany(String company) {
        return companyRepository.findByName(company).
                orElseGet(() -> companyRepository.save(new CompanyEntity(company)));
    }

    private void checkAddErrors(AddProductReq dto, String token) throws Exception {
        authenticationManager.checkToken(token);

        if (StringUtils.isEmpty(dto.getCompany())
                || StringUtils.isEmpty(dto.getName())
                || StringUtils.isEmpty(dto.getPrice())) {
            throw new Exception("null info");
        }

        UserEntity user = authenticationManager.getUserWithUsername
                (authenticationManager.findUsernameWithToken(token));

        if (!user.getType().equals("seller")) {
            throw new Exception("invalid type");
        }
    }

    @Override
    public void remove(Long id, String token) throws Exception {
        checkRemoveErrors(id, token);
        productRepository.deleteById(id);
    }

    @Override
    public void editPrice(EditProductPriceReq req, String token) throws Exception {
        checkEditPriceErrors(req, token);
        Optional<ProductEntity> optionalProduct = productRepository.findById(req.getId());
        optionalProduct.get().setPrice(req.getNewPrice());
        productRepository.save(optionalProduct.get());
    }

    private void checkEditPriceErrors(EditProductPriceReq req, String token) throws Exception {
        authenticationManager.checkToken(token);

        if (StringUtils.isEmpty(req.getId()) || StringUtils.isEmpty(req.getNewPrice())) {
            throw new Exception("null info");
        }

        checkEditErrors(req.getId(), token);
    }

    @Override
    public void editName(EditProductNameReq req, String token) throws Exception {
        checkEditNameErrors(req, token);
        Optional<ProductEntity> optionalProduct = productRepository.findById(req.getId());
        optionalProduct.get().setName(req.getNewName());
        productRepository.save(optionalProduct.get());
    }

    private void checkEditNameErrors(EditProductNameReq req, String token) throws Exception {
        authenticationManager.checkToken(token);

        if (StringUtils.isEmpty(req.getId()) || StringUtils.isEmpty(req.getNewName())) {
            throw new Exception("null info");
        }

        checkEditErrors(req.getId(), token);
    }

    private void checkEditErrors(Long productId, String token) throws Exception {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            String seller = authenticationManager.findUsernameWithToken(token);
            if (!optionalProduct.get().getSeller().getUsername().equals(seller)) {
                throw new Exception("wrong seller");
            }
        } else {
            throw new Exception("wrong product id");
        }
    }

    private void checkRemoveErrors(Long id, String token) throws Exception {
        authenticationManager.checkToken(token);

        if (StringUtils.isEmpty(id))
            throw new Exception("null info");

        String seller = authenticationManager.findUsernameWithToken(token);
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            String realSeller = optionalProduct.get().getSeller().getUsername();
            if (!realSeller.equals(seller)) {
                throw new Exception("wrong seller");
            }
        } else {
            throw new Exception("wrong product id");
        }
    }

}
