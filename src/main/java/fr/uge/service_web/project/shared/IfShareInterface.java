package fr.uge.service_web.project.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

public interface IfShareInterface extends Remote {
    IProduct addProduct(String productID, String productName, String description) throws RemoteException;

    Set<? extends IProduct> getProducts() throws RemoteException;

    Map<? extends IProduct, ? extends IOffer> getOffers() throws RemoteException;

    IUser addUser(String id, String firstName, String lastName, String address, String mail) throws RemoteException;

    IUser getUser(String id) throws RemoteException;

    Set<? extends IUser> getUsers() throws RemoteException;
}
