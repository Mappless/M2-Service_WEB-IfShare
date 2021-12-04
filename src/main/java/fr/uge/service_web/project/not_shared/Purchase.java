package fr.uge.service_web.project.not_shared;

import fr.uge.service_web.project.not_shared.database.dao.utils.TransactionUtils;
import fr.uge.service_web.project.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.project.shared.IOffer;
import fr.uge.service_web.project.shared.IPurchase;
import fr.uge.service_web.project.shared.PurchaseStatus;

import java.rmi.RemoteException;
import java.util.Objects;

public class Purchase implements IPurchase {
    private PurchaseModel model;

    Purchase(PurchaseModel model) {
        this.model = Objects.requireNonNull(model);
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
        model = TransactionUtils.update(model);
        return model.getStatus();
    }

    public void setStatus(PurchaseStatus status) {
        TransactionUtils.update(model, m -> m.setStatus(status));
    }
}
