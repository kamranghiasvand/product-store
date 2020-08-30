package com.bluebox.productstore.persistence.repository;

import com.bluebox.productstore.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaSpecificationExecutor<ProductEntity>, JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long id);
}
