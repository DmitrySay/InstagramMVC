package com.ranga.service;


import com.ranga.entities.Image;

import java.io.Serializable;
import java.util.List;

public interface IImageService<T> extends IService<T> {

    List<T> getImages();

    Long count();

    List<T> list(Integer offset, Integer maxResults);

    void deleteImage(int id);

    Image getImageById(int id);
}
