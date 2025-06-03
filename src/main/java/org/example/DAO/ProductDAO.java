package org.example.DAO;

import org.example.entity.Product;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
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

    public List<Product> getAllProduct () {
        return (List<Product>) session.createQuery("select p from Product p", List.class);
    }

    public List<Product> getProductByPrice(double prix) {
        TypedQuery<Product> query = session.createQuery("select p from Product p where p.prix > 100", Product.class);
        query.setParameter("prix", prix);
        return query.getResultList();
    }

    public List<Product> getProductByPurchaseDate(LocalDate dateAchat, LocalDate startDate, LocalDate endDate) {
        TypedQuery<Product> query = session.createQuery("select p from Product p where p.dateAchat between :startDate and :endDate", Product.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
