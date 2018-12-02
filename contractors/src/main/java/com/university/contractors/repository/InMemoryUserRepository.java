package com.university.contractors.repository;

import com.university.contractors.service.Base64PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Component
@Scope(SCOPE_SINGLETON)
// todo replace with MSQL repository.
public class InMemoryUserRepository {

    private final Map<String, User> storage = new HashMap<>();
    {
        storage.put("admin", new User("admin", new Base64PasswordEncoder().encode("admin"),
                AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
        storage.put("user",  new User("user", new Base64PasswordEncoder().encode("psw"),
                AuthorityUtils.createAuthorityList("ROLE_USER")));
    }

    private static final Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);

    public User getByUsername(String username) {
        logger.debug("Finding user by username: '{}'", username);

        final User user = storage.get(username);

        if (user != null) {
            logger.debug("'{}' user was found.", username);
            return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
        }

        logger.debug("'{}' was NOT found.", username);

        return user;
    }
}
