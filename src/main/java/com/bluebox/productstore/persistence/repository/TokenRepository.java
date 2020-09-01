package com.bluebox.productstore.persistence.repository;

import com.bluebox.productstore.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TokenRepository extends JpaSpecificationExecutor<TokenEntity>, JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByUsername(String username);
    Optional<TokenEntity> findByContext(String context);
}
