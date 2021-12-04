package fr.uge.service_web.ifshare.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface IProduct extends Remote {
    String getId() throws RemoteException;

    String getName() throws RemoteException;

    void setName(String name) throws RemoteException;

    Set<? extends IOffer> getOffers() throws RemoteException;

    String getDescription() throws RemoteException;

    void setDescription(String description) throws RemoteException;
}
