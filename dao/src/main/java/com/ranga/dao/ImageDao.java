package com.ranga.dao;

import com.ranga.entities.Image;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class ImageDao extends BaseDao<Image> implements IImageDao<Image> {

    @Autowired
    public ImageDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public List<Image> getImages() {

        log.info("Get images:");
        Criteria query = getSession().createCriteria(Image.class);
        return query.list();
    }

    @Override
    public Long count() {

        log.info("Count images:");
        return (Long) getSession()
                .createCriteria(Image.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Image> list(Integer offset, Integer maxResults) {

        log.info("List images: offset = " + offset + " maxResults = " + maxResults);
        return getSession()
                .createCriteria(Image.class)
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public void deleteImage(int id) {

        Session session = getSession();
        Image image = (Image) session.get(Image.class, id);
        session.delete(image);
        log.info("Delete image: " + id);
    }

    @Override
    public Image getImageById(int id) {
        Session session = getSession();
        Image image = (Image) session.get(Image.class, id);
        return image;
    }

}
