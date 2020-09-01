package com.bluebox.productstore.persistence.service.authentication;

import com.bluebox.productstore.persistence.entity.TokenEntity;
import com.bluebox.productstore.persistence.entity.UserEntity;
import com.bluebox.productstore.persistence.repository.TokenRepository;
import com.bluebox.productstore.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;
import static java.text.MessageFormat.format;

/**
 * @author Kamran Ghiasvand
 */
@Service
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationManagerImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void register(String username, String password, String type) throws Exception {
        checkRegisterErrors(username, password, type);
        userRepository.save(new UserEntity(username, password, type));
    }
    private void checkRegisterErrors(String username, String password, String type) throws Exception {
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)||StringUtils.isEmpty(type))
            throw new Exception("null info");
        final Optional<UserEntity> optional = userRepository.findByUsername(username);
        if (optional.isPresent())
            throw new Exception(format("username: {0} exist", username));
    }

    @Override
    public String login(String username, String password) throws Exception {
        checkLoginErrors(username, password);
        return getToken(username);
    }

    private void checkLoginErrors(String username, String password) throws Exception {
        final Optional<UserEntity> optional = userRepository.findByUsernameAndPassword(username, password);
        if (optional.isEmpty())
            throw new Exception("user not found");
    }

    @Override
    public void logout(String username, String token) throws Exception {
        final Optional<TokenEntity> optional = tokenRepository.findByUsername(username);
        checkLogoutErrors(optional);
        tokenRepository.delete(optional.get());
    }

    private void checkLogoutErrors(Optional<TokenEntity> token) throws Exception {
        if (token.isEmpty())
            throw new Exception("invalid token");
    }

    @Override
    public boolean isTokenValid(String context) {
        return tokenRepository.findByContext(context).isPresent();
    }

    @Override
    public String findUsernameWithToken(String context) throws Exception {
        if (!isTokenValid(context)) {
            throw new Exception("invalid token");
        }
        return tokenRepository.findByContext(context).get().getUsername();
    }

    @Override
    public UserEntity getUserWithUsername(String username) throws Exception {
        Optional<UserEntity> optionalUser =  userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new Exception("invalid username");
        }

        return optionalUser.get();
    }

    @Override
    public boolean isTokenExpired(String context) {
        return tokenRepository.findByContext(context).get().tokenExpired();
    }


    private String getToken(String username) {

        Optional<TokenEntity> optionalToken = tokenRepository.findByUsername(username);

        if (optionalToken.isEmpty()) {
            TokenEntity token = getNewToken(username);
            return token.getContext();
        }

        if (optionalToken.get().tokenExpired()) {
            tokenRepository.delete(optionalToken.get());
            TokenEntity token = getNewToken(username);
            return token.getContext();
        }

        return optionalToken.get().getContext();

    }

    private TokenEntity getNewToken(String username) {
        TokenEntity token = new TokenEntity(username);
        generateNewTokenContext(token);
        return token;
    }

    private void generateNewTokenContext(TokenEntity tokenEntity) {
        String context;
        do {
            context = UUID.randomUUID().toString();
        } while (tokenRepository.findByContext(context).isPresent());

        tokenEntity.setContext(context);
    }

}
