package com.bluebox.productstore.persistence.service.authentication;

import com.bluebox.productstore.persistence.entity.UserEntity;
import org.springframework.util.StringUtils;

/**
 * @author Kamran Ghiasvand
 */
public interface AuthenticationManager {
    void register(String username, String password, String type) throws Exception;

    String login(String username, String password) throws Exception;

    void logout(String username, String token) throws Exception;

    boolean isTokenValid(String token);

    String findUsernameWithToken(String token) throws Exception;

    UserEntity getUserWithUsername(String username) throws Exception;

    UserEntity getUserWithToken(String token) throws Exception;

    boolean isTokenExpired(String context) throws Exception;

    void checkToken(String token) throws Exception;
}
