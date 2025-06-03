package org.example.DAO;

import org.example.entity.Product;
import org.hibernate.Session;

import java.util.List;

public class ProductDAO {

    private static Session session;

    public ProductDAO(Session session) { this.session = session; }

    public static Product save(Product product) {
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        return product;
    }

    public boolean delete ( Product product) {
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        return true;
    }

    public Product get (int id) { return session.get(Product.class, id); }

    public List<Product> get () {
        return session.createQuery("select p from Product", Product.class).getResultList();
    }
}
