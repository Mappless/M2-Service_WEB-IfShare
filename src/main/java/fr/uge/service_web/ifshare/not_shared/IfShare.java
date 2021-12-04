package fr.uge.service_web.ifshare.not_shared;

import fr.uge.service_web.ifshare.not_shared.database.dao.OfferDAO;
import fr.uge.service_web.ifshare.not_shared.database.dao.ProductDAO;
import fr.uge.service_web.ifshare.not_shared.database.dao.UserDAO;
import fr.uge.service_web.ifshare.not_shared.exception.UncheckedRemoteException;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.not_shared.database.model.ProductModel;
import fr.uge.service_web.ifshare.not_shared.database.model.UserModel;
import fr.uge.service_web.ifshare.shared.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class IfShare extends UnicastRemoteObject implements IfShareInterface {
    public IfShare() throws RemoteException {
        super();
    }

    @Override
    public IProduct addProduct(String id, String name, String description) throws RemoteException {
        ProductModel productModel = new ProductModel(id, name, description);

        ProductDAO.addProduct(productModel);

        return new Product(productModel);
    }

    @Override
    public Set<? extends IProduct> getProducts() throws RemoteException {
        return ProductDAO.getAll().stream().map(pm -> {
            try {
                return new Product(pm);
            } catch (RemoteException e) {
                throw new UncheckedRemoteException(e);
            }
        }).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public IProduct getProduct(String id) throws RemoteException {
        return new Product(ProductDAO.getProduct(id));
    }

    @Override
    public Map<? extends IProduct, ? extends IOffer> getOffers() throws RemoteException {
        Set<OfferModel> offers = OfferDAO.getAll();

        return offers.stream().collect(
            Collectors.toUnmodifiableMap(
                om -> {
                    try {
                        return new Product(om.getProduct());
                    } catch (RemoteException e) {
                        throw new UncheckedRemoteException(e);
                    }
                },
                om -> {
                    try {
                        return new Offer(om);
                    } catch (RemoteException e) {
                        throw new UncheckedRemoteException(e);
                    }
                }
            )
        );
    }

    @Override
    public IUser addUser(String id, String firstName, String lastName, String address, String mail) throws RemoteException {
        UserModel userModel = new UserModel(id, firstName, lastName, address, mail);

        UserDAO.addUser(userModel);

        return new User(userModel);
    }

    @Override
    public IUser getUser(String id) throws RemoteException {
        return new User(UserDAO.getUser(id));
    }

    @Override
    public Set<? extends IUser> getUsers() throws RemoteException {
        return UserDAO.getUsers().stream().map(
            um -> {
                try {
                    return new User(um);
                } catch (RemoteException e) {
                    throw new UncheckedRemoteException(e);
                }
            }
        ).collect(Collectors.toUnmodifiableSet());
    }
}
