package com.ranga.service;

import com.ranga.dao.IRoleDao;
import com.ranga.dao.IUserDao;
import com.ranga.entities.Role;
import com.ranga.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService extends BaseService<User> implements IUserService<User> {


    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired(required = false)
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        roles.add((Role) roleDao.get(Role.class, 1));
        user.setRoles(roles);
        userDao.add(user);
    }
}
