package fr.uge.service_web.project.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface IUser extends Remote {
    String getId() throws RemoteException;

    String getFirstName() throws RemoteException;

    void setFirstName(String firstName) throws RemoteException;

    String getLastName() throws RemoteException;

    void setLastName(String lastName) throws RemoteException;

    String getAddress() throws RemoteException;

    void setAddress(String address) throws RemoteException;

    String getMail() throws RemoteException;

    void setMail(String mail) throws RemoteException;

    IOffer offer(IProduct product, ProductState productState, float price, int stock) throws RemoteException;

    IPurchase purchase(IOffer offer) throws RemoteException;

    IPurchase purchase(IOffer offer, int quantity) throws RemoteException;

    Set<? extends IOffer> getOffers() throws RemoteException;

    List<? extends IPurchase> getPurchases() throws RemoteException;
}
