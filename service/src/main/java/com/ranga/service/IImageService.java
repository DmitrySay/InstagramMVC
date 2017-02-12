package com.ranga.service;


import java.util.List;

public interface IImageService<T> extends IService<T> {

    List<T> getImages();

    Long count();

    List<T> list(Integer offset, Integer maxResults);

}
