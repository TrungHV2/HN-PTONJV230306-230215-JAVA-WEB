package com.ra.service.impl;

import com.ra.model.User;
import com.ra.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static List<User> users = new ArrayList<>(Arrays.asList(
        new User("U01", "admin1", "1234", true, 1, 10000, new Date()),
        new User("U02", "admin2", "1234", false, 1, 10000, new Date()),
        new User("U03", "admin3", "1234", false, 2, 10000, new Date()),
        new User("U04", "admin4", "1234", true, 2, 10000, new Date()),
        new User("U05", "admin5", "1234", true, 4, 10000, new Date())
    ));

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findId(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void edit(User user) {
        User u = findId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setBalance(user.getBalance());
        u.setStatus(user.isStatus());
        u.setBirthday(user.getBirthday());
    }

    @Override
    public void remove(String id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
