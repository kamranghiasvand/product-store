package com.bluebox.productstore.persistence.service;

import com.bluebox.productstore.persistence.entity.UserEntity;
import com.bluebox.productstore.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.text.MessageFormat.format;

/**
 * @author Kamran Ghiasvand
 */
@Service
public class AuthenticationManagerImpl implements AuthenticationManager {
    private Map<String, String> generatedToken = new HashMap<>();
    private Map<String, Long> createdTime = new HashMap<>();
    private final UserRepository userRepository;

    @Value("${security.token.expire-time: 3600000}")
    private Long expireTime;

    @Autowired
    public AuthenticationManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password) throws Exception {
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password))
            throw new Exception("null info");
        final Optional<UserEntity> optional = userRepository.findByUsername(username);
        if (optional.isPresent())
            throw new Exception(format("username: {0} exist", username));
        userRepository.save(new UserEntity(username, password));
    }

    @Override
    public String login(String username, String password) throws Exception {
        final Optional<UserEntity> optional = userRepository.findByUsernameAndPassword(username, password);
        if (!optional.isPresent())
            throw new Exception("user not found");
        return getToken(username);
    }

    @Override
    public void logout(String username, String token) {
        generatedToken.remove(username);
        createdTime.remove(token);
    }


    private String getToken(String username) {
        final String current = generatedToken.get(username);
        if (current == null || isTokenExpired(current))
            return generateNewToken(username);
        return current;
    }

    @Override
    public boolean isTokenExpired(String token) {
        if (StringUtils.isEmpty(token))
            return true;
        final Long createTime = createdTime.get(token);
        if (createTime == null)
            return true;
        return System.currentTimeMillis() - createTime >= expireTime;

    }

    private String generateNewToken(String username) {
        String token = UUID.randomUUID().toString();
        generatedToken.put(username, token);
        createdTime.put(token, System.currentTimeMillis());
        return token;
    }


}
