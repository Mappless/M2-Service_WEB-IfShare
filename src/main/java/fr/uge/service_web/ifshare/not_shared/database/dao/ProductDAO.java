package fr.uge.service_web.ifshare.not_shared.database.dao;

import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.not_shared.database.model.ProductModel;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDAO {
    public static void addProduct(ProductModel productModel) {
        TransactionUtils.inTransaction(
            (em, txn) -> {
                em.persist(productModel);
                return null;
            }
        );
    }

    public static ProductModel getProduct(String id) {
        return TransactionUtils.inTransaction((em, txn) -> em.find(ProductModel.class, id));
    }

    public static Set<ProductModel> getAll() {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<ProductModel> query = em.createQuery("SELECT p FROM ProductModel p", ProductModel.class);
                return query.getResultStream().collect(Collectors.toUnmodifiableSet());
            }
        );
    }

    public static Set<OfferModel> getOffers(ProductModel productModel) {
        return TransactionUtils.inTransaction(
            (em, txn) -> {
                TypedQuery<OfferModel> query = em.createQuery("SELECT o FROM OfferModel o WHERE o.product = :product", OfferModel.class);
                query.setParameter("product", productModel);
                return query.getResultStream().collect(Collectors.toUnmodifiableSet());
            }
        );
    }
}
