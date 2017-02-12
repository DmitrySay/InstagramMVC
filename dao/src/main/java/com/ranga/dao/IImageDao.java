package com.ranga.dao;


import java.util.List;

public interface IImageDao<T> extends Dao<T> {

    List<T> getImages();

    Long count();

    List<T> list(Integer offset, Integer maxResults);
}
