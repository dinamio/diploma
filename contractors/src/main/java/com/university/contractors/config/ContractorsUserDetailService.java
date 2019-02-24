package com.university.contractors.config;

import com.university.contractors.model.User;
import com.university.contractors.repository.UserRepository;
import com.university.contractors.service.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContractorsUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ContractorsUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found.", username));
        }

        final User user = userOptional.get();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getUserRole().getValue()));

    }

    public User loadCustomUserByUsername(String username) {
        final Optional<User> userByUsername = userRepository.findByUsername(username);

        if (!userByUsername.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", username));
        }

        return userByUsername.get();
    }
}
