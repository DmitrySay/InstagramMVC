package com.ranga.service;


import com.ranga.dao.IRoleDao;
import com.ranga.dao.IUserDao;
import com.ranga.entities.Image;
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
public class ImageServiceTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Test
    public void addImage() {

        Image image = new Image();
        image.setComment("hello");
        image.setFilename("C://");
        imageService.add(image);

        //TODO
    }

    @Test
    public void save() {
        User user = new User();

        user.setPassword("123");
        user.setUsername("user");
        Set<Role> roles = new HashSet<>();
        Role role = (Role) roleDao.get(Role.class, 1);
        roles.add(role);
        user.setRoles(roles);
        userDao.add(user);

    }

}