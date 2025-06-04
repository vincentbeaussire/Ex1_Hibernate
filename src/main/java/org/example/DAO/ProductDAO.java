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
        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    public List<Product> getAllProduct (int id) {
        return session.createQuery("select id from Product", Product.class).getResultList();
    }

    public List<Product> getProductByPrice(double prix) {
        TypedQuery<Product> query = session.createQuery("select p from Product p where p.prix > 100", Product.class);;
        return query.getResultList();
    }

    public List<Product> getProductByPurchaseDate(String reference, LocalDate startDate, LocalDate endDate) {
        TypedQuery<Product> query = session.createQuery("select reference from Product p where p.dateAchat between :startDate and :endDate", Product.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public List getReferenceByStock(String reference, int stock) {
        return session.createQuery("select reference from Product p where p.stock < :stock", List.class).getSingleResult();
    }

    public List getStockByBrand(int stock, String marque) {
        return session.createQuery("select SUM(stock) from Product p where p.marque = :marque", List.class).getSingleResult();
    }

    public List getAveragePriceProduct(double prix) {
        return session.createQuery("select AVG(prix) from Product", List.class).getSingleResult();
    }

    public List getProductByBrand(String marque, String reference) {
        return session.createQuery("select p from Product p where p.reference = :reference order by p.marque", List.class).getSingleResult();
    }

    public List deleteProductByBrand(String marque) {
        session.beginTransaction();
        session.getTransaction().commit();
        return session.createQuery("delete from Product p where p.marque = :marque", List.class).getSingleResult();
    }
}
