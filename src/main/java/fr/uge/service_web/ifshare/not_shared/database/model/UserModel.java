package fr.uge.service_web.ifshare.not_shared.database.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserModel implements Serializable {
    @Id
    @Column(name = "user_id")
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String mail;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferModel> offers;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("timestamp")
    private List<PurchaseModel> purchases;

    public UserModel() {}

    public UserModel(String id, String firstName, String lastName, String address, String mail) {
        this.id = Objects.requireNonNull(id);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = Objects.requireNonNull(address);
        this.mail = Objects.requireNonNull(mail);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = Objects.requireNonNull(address);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = Objects.requireNonNull(mail);
    }

    public Set<OfferModel> getOffers() {
        return offers;
    }

    public void setOffers(Set<OfferModel> offers) {
        this.offers = offers;
    }

    public List<PurchaseModel> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseModel> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel userModel)) return false;
        return getId().equals(userModel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
