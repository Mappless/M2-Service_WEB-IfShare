package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.dao.ProductDAO;
import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.exception.UncheckedRemoteException;
import fr.uge.service_web.ifshare.not_shared.database.model.ProductModel;
import fr.uge.service_web.ifshare.shared.IOffer;
import fr.uge.service_web.ifshare.shared.IProduct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Product extends UnicastRemoteObject implements IProduct {
    private ProductModel model;

    public Product(ProductModel model) throws RemoteException {
        super();
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public String getId() throws RemoteException {
        return model.getId();
    }

    @Override
    public String getName() throws RemoteException {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return model.getName();
    }

    @Override
    public void setName(String name) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setName(name));
    }

    @Override
    public Set<? extends IOffer> getOffers() {
        return ProductDAO.getOffers(model).stream().map(
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
    public String getDescription() throws RemoteException {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return model.getDescription();
    }

    @Override
    public void setDescription(String description) throws RemoteException {
        TransactionUtils.update(model.getClass(), model.getId(), m -> m.setDescription(description));
    }

    @Override
    public String toString() {
        model = TransactionUtils.update(model.getClass(), model.getId());
        return "Product{" +
                "model=" + model +
                '}';
    }
}
