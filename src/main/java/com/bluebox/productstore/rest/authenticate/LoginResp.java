package com.bluebox.productstore.rest.authenticate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by kamran ghiasvand
 */
@Setter
@Getter
@AllArgsConstructor
public class LoginResp {
    private String token;
    private Long expireIn;
}
