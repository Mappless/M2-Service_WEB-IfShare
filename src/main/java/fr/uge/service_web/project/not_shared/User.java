package fr.uge.service_web.project.not_shared;

import fr.uge.service_web.project.shared.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements IUser {
    @Id
    @Column(name = "user_id")
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String mail;

    public User() {}

    public User(String id, String firstName, String lastName, String address, String mail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mail = mail;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public void setId(String id) throws RemoteException {
        this.id = id;
    }

    @Override
    public String getFirstName() throws RemoteException {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) throws RemoteException {
        this.firstName = Objects.requireNonNull(firstName);
    }

    @Override
    public String getLastName() throws RemoteException {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) throws RemoteException {
        this.lastName = Objects.requireNonNull(lastName);
    }

    @Override
    public String getAddress() throws RemoteException {
        return address;
    }

    @Override
    public void setAddress(String address) throws RemoteException {
        this.address = Objects.requireNonNull(address);
    }

    @Override
    public String getMail() throws RemoteException {
        return mail;
    }

    @Override
    public void setMail(String mail) throws RemoteException {
        this.mail = Objects.requireNonNull(mail);
    }

    @Override
    public IOffer offer(IProduct product, ProductState productState, float price, int stock) throws RemoteException {
        return null;
    }

    @Override
    public IPurchase purchase(IOffer offer) throws RemoteException {
        return null;
    }

    @Override
    public List<IOffer> getOffers() throws RemoteException {
        return null;
    }

    @Override
    public List<IPurchase> getPurchases() throws RemoteException {
        return null;
    }
}
