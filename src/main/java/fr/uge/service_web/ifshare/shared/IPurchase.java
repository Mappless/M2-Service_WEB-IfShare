package fr.uge.service_web.ifshare.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IPurchase extends Remote {
    IUser getBuyer() throws RemoteException;

    IOffer getOffer() throws RemoteException;

    int getQuantity() throws RemoteException;

    PurchaseStatus getStatus() throws RemoteException;

    Date getPurchaseDate() throws RemoteException;
}
