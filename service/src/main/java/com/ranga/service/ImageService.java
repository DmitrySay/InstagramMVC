package com.ranga.service;


import com.ranga.dao.Dao;
import com.ranga.dao.IImageDao;
import com.ranga.entities.Image;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageService extends BaseService<Image> implements IImageService<Image> {

    @Autowired
    public IImageDao imageDao;

    @Autowired
    public ImageService(Dao<Image> baseDao) {
        super(baseDao);
    }



    @Override
    public List<Image> list(Integer offset, Integer maxResults) {
        return imageDao.list(offset, maxResults);
    }

    @Override
    public List<Image> getImages() {
        return imageDao.getImages();
    }

    @Override
    public Long count() {
        return imageDao.count();
    }

    @Override
    public void deleteImage(int id) {
        imageDao.deleteImage(id);

    }

    @Override
    public Image getImageById(int id) {
        return imageDao.getImageById(id);
    }


}








