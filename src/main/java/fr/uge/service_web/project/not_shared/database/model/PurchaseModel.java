package fr.uge.service_web.project.not_shared.database.model;

import fr.uge.service_web.project.not_shared.Offer;
import fr.uge.service_web.project.shared.IOffer;
import fr.uge.service_web.project.shared.IPurchase;
import fr.uge.service_web.project.shared.PurchaseStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.RemoteException;
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
    private Offer offer;
    private int quantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    public PurchaseModel() {}

    public PurchaseModel(Offer offer, int quantity, PurchaseStatus status) {
        this.offer = Objects.requireNonNull(offer);

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity can't be negative.");
        this.quantity = quantity;
        this.status = status;
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

    public IOffer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
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
}
