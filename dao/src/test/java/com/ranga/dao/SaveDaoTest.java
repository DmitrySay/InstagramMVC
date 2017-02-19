package com.ranga.dao;


import com.ranga.entities.Role;
import com.ranga.entities.User;
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
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class SaveDaoTest {


    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Test
    public void createRoleAndUser() {
        User user = new User();
        user.setPassword("123");
        user.setUsername("user");
        userDao.add(user);

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        roleDao.add(role);
    }

    @Test
    public void findByUserName() {

        String username = "user";

        User user = (User) userDao.findByUsername(username);
        System.out.println("******* " + user.toString());
    }


    @Test
    public void save() {

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