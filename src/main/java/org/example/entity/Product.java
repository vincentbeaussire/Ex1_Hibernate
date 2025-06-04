package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marque;
    private String reference;
    private LocalDate dateAchat;
    private double prix;
    private int stock;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "product_image",
    joinColumns = @JoinColumn(name = "productId"),
    inverseJoinColumns = @JoinColumn(name = "imageId"))
    private List<Image> images;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "product_comment",
    joinColumns = @JoinColumn(name = "productid"),
    inverseJoinColumns = @JoinColumn(name = "commentId"))
    private List<Comment> comments;

    public void addImage(Image image) {
        images.add(image);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
