package fr.uge.service_web.ifshare.shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

public interface IPurchase extends Serializable {
    IUser getBuyer() throws RemoteException;

    IOffer getOffer() throws RemoteException;

    int getQuantity();

    PurchaseStatus getStatus();

    Date getPurchaseDate();
}
