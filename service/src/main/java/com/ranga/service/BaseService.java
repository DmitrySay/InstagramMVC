package com.ranga.service;


import com.ranga.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BaseService<T> implements IService<T> {


    private Dao<T> baseDao;

    public BaseService() {
    }

    @Autowired
    public BaseService(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);

    }

    @Override
    public T get(Class<T> clazz, Serializable id) {
        return baseDao.get(clazz, id);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void refresh(T t) {
        baseDao.refresh(t);
    }
}