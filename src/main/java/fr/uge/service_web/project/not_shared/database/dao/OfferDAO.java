package fr.uge.service_web.project.not_shared.database.dao;

import fr.uge.service_web.project.not_shared.database.dao.utils.TransactionUtils;
import fr.uge.service_web.project.not_shared.database.model.OfferModel;
import fr.uge.service_web.project.not_shared.database.model.ProductModel;
import fr.uge.service_web.project.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.project.not_shared.database.model.UserModel;
import fr.uge.service_web.project.shared.ProductState;
import fr.uge.service_web.project.shared.PurchaseStatus;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferDAO {
    public static OfferModel addOffer(UserModel userModel, ProductModel productModel, ProductState state, float price, int stock) {
        OfferModel offerModel = new OfferModel(userModel, productModel, state, price, stock);

        TransactionUtils.inTransaction(
            (em, txn) ->  {
                em.persist(offerModel);
                return null;
            }
        );

        return offerModel;
    }

    public static OfferModel getOffer(int id) {
        return TransactionUtils.inTransaction((em, txn) -> em.find(OfferModel.class, id));
    }

    public static Set<OfferModel> getAll() {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<OfferModel> query = em.createQuery("SELECT o FROM OfferModel o JOIN FETCH o.product JOIN FETCH o.seller", OfferModel.class);
                return query.getResultStream().collect(Collectors.toUnmodifiableSet());
            }
        );
    }

    public static List<PurchaseModel> getWaitingPurchases(OfferModel offerModel) {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<PurchaseModel> query = em.createQuery("SELECT p FROM PurchaseModel p WHERE p.offer = :offer AND p.status = :status ORDER BY p.timestamp", PurchaseModel.class);
                query.setParameter("offer", offerModel);
                query.setParameter("status", PurchaseStatus.WAITING);
                return query.getResultStream().toList();
            }
        );
    }
}
