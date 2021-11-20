package fr.uge.service_web.project.shared;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface IPurchase extends Serializable {
    IOffer getOffer() throws RemoteException;

    int getQuantity();

    PurchaseStatus getStatus();
}
