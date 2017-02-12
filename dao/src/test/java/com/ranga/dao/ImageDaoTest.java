package com.ranga.dao;

import com.ranga.entities.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:/testConfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ImageDaoTest {

    @Autowired
    private IImageDao imageDao;


    @Test
    public void addImage() {

        Image image = new Image();
        image.setComment("hello");
        image.setFilename("C://");
        imageDao.add(image);

        //TODO
    }


}
