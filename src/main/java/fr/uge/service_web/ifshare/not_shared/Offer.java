package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.dao.OfferDAO;
import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.shared.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Objects;

public class Offer extends UnicastRemoteObject implements IOffer {
    private OfferModel model;

    public Offer(OfferModel model) throws RemoteException {
        super();
        this.model = Objects.requireNonNull(model);
    }

    public static Offer getOffer(int id) throws RemoteException {
        return new Offer(OfferDAO.getOffer(id));
    }

    @Override
    public int getId() throws RemoteException {
        return model.getId();
    }

    @Override
    public IUser getSeller() throws RemoteException {
        return new User(model.getSeller());
    }

    @Override
    public IProduct getProduct() throws RemoteException {
        return new Product(model.getProduct());
    }

    @Override
    public ProductState getProductState() throws RemoteException {
        return model.getProductState();
    }

    @Override
    public float getPrice() throws RemoteException {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return model.getPrice();
    }

    @Override
    public void setPrice(float price) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setPrice(price));
    }

    @Override
    public int getStock() throws RemoteException {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return model.getStock();
    }

    @Override
    public void refill(int quantity) throws RemoteException {
        if (quantity <= 0)
            throw new IllegalArgumentException("Refill quantity must be positive");

        TransactionUtils.update(model.getClass(), model.getId(), m -> model.setStock(model.getStock() + quantity));
        processPurchases();
    }

    public void processPurchases() throws RemoteException {
        int stock = getStock();
        List<Purchase> purchases = OfferDAO.getWaitingPurchases(model).stream()
                .map(Purchase::new)
                .toList();

        for (Purchase purchase : purchases) {
            if (purchase.getQuantity() <= stock) {
                stock -= purchase.getQuantity();
                purchase.setStatus(PurchaseStatus.DONE);
            }

            else
                break;
        }

        int currentStock = stock;
        TransactionUtils.update(model.getClass(), model.getId(), m -> model.setStock(currentStock));
    }

    @Override
    public String toString() {
        return "Offer{" +
                "model=" + model +
                '}';
    }
}
