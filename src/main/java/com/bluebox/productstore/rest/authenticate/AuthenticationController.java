package com.bluebox.productstore.rest.authenticate;

import com.bluebox.productstore.persistence.service.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kamran Ghiasvand
 */
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationManager manager;

    @Autowired
    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody UserDto dto) throws Exception {
        manager.register(dto.getUsername(), dto.getPassword());
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserDto dto) throws Exception {
        return manager.login(dto.getUsername(), dto.getPassword());
    }
}
