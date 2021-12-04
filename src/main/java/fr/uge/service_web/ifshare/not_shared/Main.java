package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.shared.IfShareInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1200);
        IfShareInterface ifShare = new IfShare();
        Naming.rebind("rmi://localhost:1200/IfShareService", ifShare);
    }
}
