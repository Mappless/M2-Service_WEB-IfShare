package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.dao.OfferDAO;
import fr.uge.service_web.ifshare.not_shared.database.dao.ProductDAO;
import fr.uge.service_web.ifshare.not_shared.database.dao.PurchaseDAO;
import fr.uge.service_web.ifshare.not_shared.database.dao.UserDAO;
import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.exception.UncheckedRemoteException;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.not_shared.database.model.ProductModel;
import fr.uge.service_web.ifshare.not_shared.database.model.UserModel;
import fr.uge.service_web.ifshare.shared.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class User extends UnicastRemoteObject implements IUser {
    private UserModel model;

    public User(UserModel userModel) throws RemoteException {
        super();
        this.model = Objects.requireNonNull(userModel);
    }

    @Override
    public String getId() throws RemoteException {
        return model.getId();
    }

    @Override
    public String getFirstName() throws RemoteException {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return model.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setFirstName(firstName));
    }

    @Override
    public String getLastName() throws RemoteException {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return model.getLastName();
    }

    @Override
    public void setLastName(String lastName) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setLastName(lastName));
    }

    @Override
    public String getAddress() throws RemoteException {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return model.getAddress();
    }

    @Override
    public void setAddress(String address) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setAddress(address));
    }

    @Override
    public String getMail() throws RemoteException {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return model.getMail();
    }

    @Override
    public void setMail(String mail) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setMail(mail));
    }

    @Override
    public IOffer offer(IProduct product, ProductState productState, float price, int stock) throws RemoteException {
        ProductModel pm = ProductDAO.getProduct(product.getId());

        return new Offer(OfferDAO.addOffer(model, pm, productState, price, stock));
    }


    @Override
    public IPurchase purchase(IOffer offer, int quantity) throws RemoteException {
        OfferModel offerModel = OfferDAO.getOffer(offer.getId());

        Purchase purchase = new Purchase(PurchaseDAO.addPurchase(model, offerModel, quantity));
        Offer.getOffer(offer.getId()).processPurchases();

        return purchase;
    }

    @Override
    public IPurchase purchase(IOffer offer) throws RemoteException {
        return purchase(offer, 1);
    }

    @Override
    public Set<? extends IOffer> getOffers() throws RemoteException {
        return UserDAO.getOffers(model).stream().map(
            om -> {
                try {
                    return new Offer(om);
                } catch (RemoteException e) {
                    throw new UncheckedRemoteException(e);
                }
            }
        ).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public List<? extends IPurchase> getPurchases() throws RemoteException {
        return UserDAO.getPurchases(model).stream().map(pm -> {
            try {
                return new Purchase(pm);
            } catch (RemoteException e) {
                throw new UncheckedRemoteException(e);
            }
        }).toList();
    }

    @Override
    public String toString() {
        model = TransactionUtils.load(model.getClass(), model.getId());
        return "User{" +
                "model=" + model +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return model.equals(user.model);
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }
}
