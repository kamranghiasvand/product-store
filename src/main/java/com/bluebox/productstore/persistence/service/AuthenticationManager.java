package com.bluebox.productstore.persistence.service;

/**
 * @author Kamran Ghiasvand
 */
public interface AuthenticationManager {
    void register(String username, String password) throws Exception;

    String login(String username, String password) throws Exception;

    void logout(String username, String token);

    boolean isTokenExpired(String token);
}
