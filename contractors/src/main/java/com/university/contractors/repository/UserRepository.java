package com.university.contractors.repository;

import com.university.contractors.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    private final static Map<String, User> inMemoryDb = new HashMap<>();

    static {
        inMemoryDb.put("admin", new User("admin", "pass"));
    }


    public User findByUsername(String username) {
        return inMemoryDb.get(username);
    }
}
