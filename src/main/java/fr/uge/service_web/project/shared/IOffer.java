package fr.uge.service_web.project.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface IOffer extends Remote {
    int getId() throws RemoteException;

    IUser getSeller() throws RemoteException;

    IProduct getProduct() throws RemoteException;

    ProductState getProductState() throws RemoteException;

    float getPrice() throws RemoteException;

    void setPrice(float price) throws RemoteException;

    int getStock() throws RemoteException;

    void refill(int quantity) throws RemoteException;

    Set<? extends IPurchase> getPurchases() throws RemoteException;
}
