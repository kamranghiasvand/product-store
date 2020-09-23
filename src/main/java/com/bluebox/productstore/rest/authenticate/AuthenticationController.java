package com.bluebox.productstore.rest.authenticate;

import com.bluebox.productstore.persistence.service.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kamran Ghiasvand
 */
@RestController
@RequestMapping(path = "/api/authentication", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final AuthenticationManager manager;

    @Value("${security.token.expire-time: 3600000}")
    private Long expireTime;

    @Autowired
    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(@RequestBody UserDto dto) throws Exception {
        manager.register(dto.getUsername(), dto.getPassword(), dto.getType());
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResp login(@RequestBody UserDto dto) throws Exception {
        final String token = manager.login(dto.getUsername(), dto.getPassword());
        return new LoginResp(token, expireTime);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(@RequestBody LogoutReq req, @RequestHeader String token) throws Exception {
        manager.logout(req.getUsername(), token);
    }
}
