package com.ranga.service;


import com.ranga.entities.Image;
import com.ranga.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/testConfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ImageServiceTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IUserService userService;

    @Test
    public void addImageTest() {

        Image image = new Image();
        image.setComment("hello");
        image.setFilename("C://");

        imageService.add(image);

    }

    @Test
    public void findByUserNameTest() {

        String username = "admin";
        User user = (User) userService.findByUsername(username);
        assertEquals("Username not equals", "admin", user.getUsername());
    }


    @Test
    public void deleteImageTest() {

        List<Image> list = imageService.getImages();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        int oldSize = list.size();
        Image im = list.get(list.size() - 1);

        imageService.deleteImage(im.getId());

        List<Image> newlist = imageService.getImages();
        Assert.assertNotNull(newlist);
        int newSize = newlist.size();

        Assert.assertEquals("Delete is not work", newSize, oldSize - 1);

    }

    @Test
    public void getImageTest() {


        Image image = (Image) imageService.get(Image.class, 11);
        System.out.println(image.toString());

    }


}
