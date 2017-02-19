package com.ranga.service;

import com.ranga.dao.IRoleDao;
import com.ranga.dao.IUserDao;
import com.ranga.entities.Role;
import com.ranga.entities.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class CreateAdminTest {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired(required = true)
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Create user with login admin/password 12345678 when database is empty
     * set two roles : ROLE_USER, ROLE_ADMIN
     */

    @Test
    @Ignore
    public void createAdminTest() {

        Role roleUser = new Role();
        roleUser.setId(1);
        roleUser.setName("ROLE_USER");
        roleDao.add(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setId(2);
        roleAdmin.setName("ROLE_ADMIN");
        roleDao.add(roleAdmin);


        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode("123"));
        user.setUsername("admin");

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleAdmin);

        user.setRoles(roles);
        userDao.add(user);

    }




}
