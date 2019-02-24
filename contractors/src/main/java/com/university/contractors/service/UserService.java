package com.university.contractors.service;

import com.university.contractors.controller.payload.SignUpUser;
import com.university.contractors.model.User;
import com.university.contractors.model.UserRole;
import com.university.contractors.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final UserRole DEFAULT_ROLE = UserRole.USER;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }


    public void signUpUser(SignUpUser userToSignUp) {
        final String password = userToSignUp.getPassword();
        final String confirmationPassword = userToSignUp.getConfirmationPassword();

        if (ObjectUtils.notEqual(password, confirmationPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        final String username = userToSignUp.getUsername();

        if (usernameIsNotUnique(username)) {
            throw new UsernameAlreadyRegisteredException();
        }

        final User user = new User();
        user.setUsername(username);
        user.setUserRole(DEFAULT_ROLE);

        final String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);
    }

    private boolean usernameIsNotUnique(String username) {
        final Optional<User> userByUsernameOptional = userRepository.findByUsername(username);
        return userByUsernameOptional.isPresent();
    }

    void saveUserToken(String username, String token) {
        final Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        final User user = optionalUser.get();
        user.setToken(token);
        userRepository.save(user);
    }
}
