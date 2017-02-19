package com.ranga.dao;


import com.ranga.entities.Role;
import com.ranga.entities.User;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@ContextConfiguration("/testConfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SaveDaoTest {


    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Test
    public void findByUserNameTest() {

        String username = "admin";
        User user = (User) userDao.findByUsername(username);
        assertEquals("Username not equals","admin", user.getUsername());
    }


    @Test
    public void saveTest() {

        Set<Role> roles = new HashSet<>();
        Role role = (Role) roleDao.get(Role.class, 1);
        roles.add(role);

        User user = new User();
        user.setUsername("name");
        user.setPassword("pass");
        user.setRoles(roles);

        userDao.add(user);

    }

}