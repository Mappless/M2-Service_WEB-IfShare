package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.ifshare.shared.IOffer;
import fr.uge.service_web.ifshare.shared.IPurchase;
import fr.uge.service_web.ifshare.shared.IUser;
import fr.uge.service_web.ifshare.shared.PurchaseStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Purchase extends UnicastRemoteObject implements IPurchase {
    private PurchaseModel model;

    Purchase(PurchaseModel model) throws RemoteException {
        super();
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public IUser getBuyer() throws RemoteException {
        return new User(model.getBuyer());
    }

    @Override
    public IOffer getOffer() throws RemoteException {
        return new Offer(model.getOffer());
    }

    @Override
    public int getQuantity() throws RemoteException {
        return model.getQuantity();
    }


    @Override
    public PurchaseStatus getStatus() throws RemoteException {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return model.getStatus();
    }

    @Override
    public Date getPurchaseDate() throws RemoteException {
        return model.getTimestamp();
    }

    public void setStatus(PurchaseStatus status) {
        TransactionUtils.load(model.getClass(), model.getId(), m -> m.setStatus(status));
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "model=" + model +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase purchase)) return false;
        return model.equals(purchase.model);
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }
}
