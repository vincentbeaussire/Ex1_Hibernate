package org.example;

import org.example.DAO.ProductDAO;
import org.example.entity.Product;
import org.example.util.SessionFactorySingleton;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO(SessionFactorySingleton.getSession());

        Product product1 = Product.builder().marque("Xiaomi").reference("123456789").dateAchat(LocalDate.of(2025, 06, 03)).prix(199).stock(500).build();
        Product product2 = Product.builder().marque("Apple").reference("987654321").dateAchat(LocalDate.of(2024, 05, 06)).prix(999).stock(1000).build();
        Product product3 = Product.builder().marque("Samsung").reference("134679258").dateAchat(LocalDate.of(2023, 10, 02)).prix(550).stock(680).build();
        Product product4 = Product.builder().marque("Dell").reference("321654987").dateAchat(LocalDate.of(2025, 12, 23)).prix(449).stock(60).build();
        Product product5 = Product.builder().marque("LG").reference("258147369").dateAchat(LocalDate.of(2022, 04, 25)).prix(350).stock(30).build();

        ProductDAO.save(product1);
        ProductDAO.save(product2);
        ProductDAO.save(product3);
        ProductDAO.save(product4);
        ProductDAO.save(product5);

        Product getProduct = productDAO.get(2);
        productDAO.save(getProduct);

        Product deleteProduct = productDAO.get(3);
        productDAO.delete(deleteProduct);
        productDAO.save(deleteProduct);

        Product setProduct = productDAO.get(1);
        setProduct.setMarque("Apple");
        productDAO.save(setProduct);

        Product getAllProduct = (Product) productDAO.getAllProduct(1);
        productDAO.save(getAllProduct);

        Product getProductByPrice = (Product) productDAO.getProductByPrice(100);
        productDAO.save(getProductByPrice);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir la date d'achat des produits commandés.");
        String orderProduct = scanner.next();

        System.out.println("Veuillez saisir le numéro et la référence du produit.");
        String numRef = scanner.next();
    }
}
