package fr.uge.service_web.project.not_shared;

import fr.uge.service_web.project.shared.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class IfShare extends UnicastRemoteObject implements IfShareInterface {
    private final HashMap<IProduct, List<Offer>> offers = new HashMap<>();
    private final HashMap<String, Product> products = new HashMap<>();

    public IfShare() throws RemoteException {
        super();
    }

    @Override
    public IProduct addProduct(String productID, String productName, String description) throws RemoteException {
        Product newProduct = new Product(productID, productName, description);

        synchronized (products) {
            products.put(productID, newProduct);
        }

        return newProduct;
    }

    @Override
    public Set<IProduct> getProducts() throws RemoteException {
        synchronized (offers) {
            return new HashSet<>(offers.keySet());
        }
    }

    @Override
    public void addOffer(IUser seller, IProduct product, ProductState productState, float price, int stock) throws RemoteException {
        Offer newOffer = new Offer(id, seller, product, productState, price, stock);

        synchronized (offers) {
            offers.compute(product, (key, value) -> {
                if (value == null)
                    value = new ArrayList<>();

                value.add(newOffer);
                return value;
            });
        }
    }

    @Override
    public Optional<List<IOffer>> getOffers(IProduct product) throws RemoteException {
        synchronized (offers) {
            List<? extends IOffer> offersForProduct = offers.get(product);

            if (offersForProduct == null)
                return Optional.empty();

            return Optional.of(new ArrayList<>(offersForProduct));
        }
    }
}
