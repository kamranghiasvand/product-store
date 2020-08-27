package com.bluebox.productstore.persistence.repository;

import com.bluebox.productstore.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Kamran Ghiasvand
 */
public interface UserRepository extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
