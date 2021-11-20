package fr.uge.service_web.project.not_shared.database.model;

import fr.uge.service_web.project.shared.IUser;

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
}
