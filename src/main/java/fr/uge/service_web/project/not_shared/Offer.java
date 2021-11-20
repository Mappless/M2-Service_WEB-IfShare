package fr.uge.service_web.project.not_shared;

import fr.uge.service_web.project.shared.IOffer;
import fr.uge.service_web.project.shared.IUser;
import fr.uge.service_web.project.shared.IProduct;
import fr.uge.service_web.project.shared.ProductState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

public class Offer extends UnicastRemoteObject implements IOffer {
    private final int id;
    private final IUser seller;
    private final IProduct product;
    private final ProductState productState;
    private float price;
    private int stock;
    private final ArrayBlockingQueue<Command> waitingQueue = new ArrayBlockingQueue<>(50);

    public Offer(int id, IUser seller, IProduct product, ProductState productState, float price, int stock) throws RemoteException {
        super();
        this.id = id;
        this.seller = Objects.requireNonNull(seller);
        this.product = Objects.requireNonNull(product);
        this.productState = productState;

        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;

        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative.");
        this.stock = stock;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    public IUser getSeller() throws RemoteException {
        return seller;
    }

    public IProduct getProduct() throws RemoteException {
        return product;
    }

    public ProductState getProductState() throws RemoteException {
        return productState;
    }

    public float getPrice() throws RemoteException {
        return price;
    }

    public int getStock() throws RemoteException {
        return stock;
    }
}
