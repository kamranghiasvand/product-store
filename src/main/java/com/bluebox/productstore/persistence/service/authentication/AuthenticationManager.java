package com.bluebox.productstore.persistence.service.authentication;

/**
 * @author Kamran Ghiasvand
 */
public interface AuthenticationManager {
    void register(String username, String password, String type) throws Exception;

    String login(String username, String password) throws Exception;

    void logout(String username, String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token);

    String findUsernameWithToken(String token);
}
