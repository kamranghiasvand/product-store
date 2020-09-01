package com.bluebox.productstore.rest.authenticate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Kamran Ghiasvand
 */
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String type;
    private String token;
}
