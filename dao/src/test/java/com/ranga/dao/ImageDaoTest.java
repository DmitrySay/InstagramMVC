package com.ranga.dao;

import com.ranga.entities.Image;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        image.setComment("привет");
        image.setFilename("C://");

        Image persistent = (Image) imageDao.add(image);
        assertNotNull(persistent.getId());

        persistent = (Image) imageDao.get(Image.class, persistent.getId());
        assertEquals("Image not persist", image, persistent);

    }

    @Test
    public void deleteImage() {
        Image image = new Image();
        image.setComment("привет");
        image.setFilename("C://");
        imageDao.add(image);

        List<Image> list = imageDao.getImages();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        int oldSize = list.size();
        Image im = list.get(list.size() - 1);
        imageDao.delete(im);


        List<Image> newlist = imageDao.getImages();
        Assert.assertNotNull(newlist);
        int newSize = newlist.size();

        Assert.assertEquals(newSize, oldSize - 1);
    }

    @Test
    @Ignore
    public void getImages() {
        List<Image> list = imageDao.getImages();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);

    }

    @Test
    public void updateImage() {

        Image im = new Image();
        im.setComment("привет");
        im.setFilename("C://");
        imageDao.add(im);

        List<Image> list = imageDao.getImages();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Image image = list.get(list.size() - 1);

        image.setComment("HI");
        image.setFilename("F://");

        imageDao.update(image);

        List<Image> newlist = imageDao.getImages();
        Image newimage = newlist.get(list.size() - 1);

        Assert.assertEquals("Check id equals",newimage.getId(), image.getId());
        Assert.assertEquals(newimage.getComment(), "HI");
        Assert.assertEquals(newimage.getFilename(), "F://");

    }

}
