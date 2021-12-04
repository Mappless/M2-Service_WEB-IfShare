package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.ifshare.shared.IOffer;
import fr.uge.service_web.ifshare.shared.IPurchase;
import fr.uge.service_web.ifshare.shared.IUser;
import fr.uge.service_web.ifshare.shared.PurchaseStatus;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;

public class Purchase implements IPurchase {
    private PurchaseModel model;

    Purchase(PurchaseModel model) {
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
    public int getQuantity() {
        return model.getQuantity();
    }


    @Override
    public PurchaseStatus getStatus() {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return model.getStatus();
    }

    @Override
    public Date getPurchaseDate() {
        return model.getTimestamp();
    }

    public void setStatus(PurchaseStatus status) {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setStatus(status));
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "model=" + model +
                '}';
    }
}
