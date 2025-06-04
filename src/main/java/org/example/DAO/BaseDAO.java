package org.example.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseDAO<T> {
    protected SessionFactory sessionFactory;
    protected Class<T> type;
    protected Session session;

    public BaseDAO(SessionFactory sessionFactory,Class<T> type) {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    public List getProductAvg4OrMore(int note) {
        return session.createQuery("select AVG(note) >= 4 from Product p", List.class).getSingleResult();
    }
}
