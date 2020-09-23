package com.bluebox.productstore.persistence.repository;

import com.bluebox.productstore.persistence.entity.CartEntity;
import com.bluebox.productstore.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CartRepository extends JpaSpecificationExecutor<CartEntity>, JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findById(Long id);
}
