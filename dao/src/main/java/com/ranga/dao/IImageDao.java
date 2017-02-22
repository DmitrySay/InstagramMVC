package com.ranga.dao;


import com.ranga.entities.Image;

import java.util.List;

public interface IImageDao<T> extends Dao<T> {

    List<T> getImages();

    Long count();

    List<T> list(Integer offset, Integer maxResults);

    void deleteImage(int id);

    Image getImageById(int id);
}
