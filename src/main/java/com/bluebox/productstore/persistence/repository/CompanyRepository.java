package com.bluebox.productstore.persistence.repository;

import com.bluebox.productstore.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CompanyRepository extends JpaSpecificationExecutor<CompanyEntity>, JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findById(Long id);
    Optional<CompanyEntity> findByName(String name);
}
