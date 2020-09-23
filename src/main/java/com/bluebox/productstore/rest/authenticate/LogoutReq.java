package com.bluebox.productstore.rest.authenticate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutReq {
    private String username;
    private String password;
}
