package fr.uge.service_web.project.not_shared;

import fr.uge.service_web.project.not_shared.database.model.ProductModel;
import fr.uge.service_web.project.shared.IProduct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Product extends UnicastRemoteObject implements IProduct {
    private final ProductModel model;

    public Product(ProductModel model) throws RemoteException {
        super();
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public String getId() throws RemoteException {
        return model.getId();
    }

    @Override
    public void setId(String id) throws RemoteException {
        model.setId(id);
    }

    @Override
    public String getName() throws RemoteException {
        return model.getName();
    }

    @Override
    public void setName(String name) throws RemoteException {
        model.setName(name);
    }

    @Override
    public String getDescription() throws RemoteException {
        return model.getDescription();
    }

    @Override
    public void setDescription(String description) throws RemoteException {
        model.setDescription(description);
    }
}
