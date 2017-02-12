package com.ranga.dao;


import com.ranga.entities.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository()
public class RoleDao extends BaseDao<Role> implements IRoleDao<Role> {

    @Autowired
    public RoleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
