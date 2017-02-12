package com.ranga.dao;

import com.ranga.entities.User;


public interface IUserDao<T> extends Dao<T>  {

    User findByUsername(String username);
}
