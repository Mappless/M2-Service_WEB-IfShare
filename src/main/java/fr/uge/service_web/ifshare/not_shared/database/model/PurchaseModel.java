package fr.uge.service_web.ifshare.not_shared.database.model;

import fr.uge.service_web.ifshare.shared.PurchaseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PURCHASE")
public class PurchaseModel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private int id;
    @ManyToOne
    private UserModel buyer;
    @ManyToOne
    private OfferModel offer;
    private int quantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    public PurchaseModel() {}

    public PurchaseModel(OfferModel offer, UserModel buyer, int quantity) {
        this.offer = Objects.requireNonNull(offer);
        this.buyer = Objects.requireNonNull(buyer);

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity can't be negative.");
        this.quantity = quantity;
        this.status = PurchaseStatus.WAITING;
        this.timestamp = Date.from(Instant.now());
    }

    public void add(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity can't be negative.");

        this.quantity += quantity;
    }

    public void remove(int quantity) {
        if (quantity <= 0 || this.quantity - quantity <= 0)
            throw new IllegalArgumentException("Quantity can't be negative.");

        this.quantity -= quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OfferModel getOffer() {
        return offer;
    }

    public void setOffer(OfferModel offer) {
        this.offer = Objects.requireNonNull(offer);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity can't be negative.");
        this.quantity = quantity;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public UserModel getBuyer() {
        return buyer;
    }

    public void setBuyer(UserModel buyer) {
        this.buyer = buyer;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", offer=" + offer +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseModel purchaseModel)) return false;
        return getId() == purchaseModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
