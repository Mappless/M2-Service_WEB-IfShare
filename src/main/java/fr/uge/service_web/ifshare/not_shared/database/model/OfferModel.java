package fr.uge.service_web.ifshare.not_shared.database.model;

import fr.uge.service_web.ifshare.not_shared.Offer;
import fr.uge.service_web.ifshare.shared.ProductState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "OFFER")
public class OfferModel implements Serializable {
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

    public OfferModel() {}

    public OfferModel(UserModel seller, ProductModel product, ProductState productState, float price, int stock) {
        this.seller = Objects.requireNonNull(seller);
        this.product = Objects.requireNonNull(product);
        this.productState = productState;

        if (price < 0)
            throw new IllegalArgumentException("Price can't be negative.");
        this.price = price;

        if (stock < 0)
            throw new IllegalArgumentException("Stock can't be negative.");
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getSeller() {
        return seller;
    }

    public void setSeller(UserModel seller) {
        this.seller = Objects.requireNonNull(seller);
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = Objects.requireNonNull(product);
    }

    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState productState) {
        this.productState = Objects.requireNonNull(productState);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "OfferModel{" +
                "id=" + id +
                ", seller=" + seller +
                ", product=" + product +
                ", productState=" + productState +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferModel offerModel)) return false;
        return getId() == offerModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
