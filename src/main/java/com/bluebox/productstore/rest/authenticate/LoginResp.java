package com.bluebox.productstore.rest.authenticate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author by kamran ghiasvand
 */
@Setter
@Getter
public class LoginResp {
    private String token;
    private Long expireIn;

    public LoginResp(String token, Long expireIn) {
        this.token = token;
        this.expireIn = expireIn;
    }
}
