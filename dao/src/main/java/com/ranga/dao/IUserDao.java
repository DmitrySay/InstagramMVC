package com.ranga.dao;


public interface IUserDao<T> extends Dao<T> {

    T findByUsername(String username);
}
