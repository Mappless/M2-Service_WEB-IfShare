package fr.uge.service_web.project.not_shared.database.model;

import fr.uge.service_web.project.shared.ProductState;

import javax.persistence.*;

@Entity
@Table(name = "OFFER")
public class OfferModel {
    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private int id;
    @ManyToOne
    private UserModel seller;
    @ManyToOne
    private ProductModel product;
    @Enumerated(EnumType.STRING)
    private ProductState productState;
    private float price;
    private int stock;
}
