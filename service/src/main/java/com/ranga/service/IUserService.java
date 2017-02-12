package com.ranga.service;

public interface IUserService<T> extends IService<T> {
    T findByUsername(String username);
}
