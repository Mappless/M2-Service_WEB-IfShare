package fr.uge.service_web.ifshare.not_shared.database.dao;

import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.ifshare.not_shared.database.model.UserModel;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDAO {
    public static void addUser(UserModel userModel) {
        TransactionUtils.inTransaction(
            (em, txn) -> {
                em.persist(userModel);
                return null;
            }
        );
    }

    public static UserModel getUser(String id) {
        return TransactionUtils.inTransaction((em, txn) -> em.find(UserModel.class, id));
    }

    public static List<UserModel> getUsers() {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<UserModel> query = em.createQuery("SELECT u FROM UserModel u", UserModel.class);
                return query.getResultList();
            }
        );
    }

    public static Set<OfferModel> getOffers(UserModel userModel) {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<OfferModel> query = em.createQuery("SELECT o FROM OfferModel o WHERE o.seller = :user", OfferModel.class);
                query.setParameter("user", userModel);
                return query.getResultStream().collect(Collectors.toUnmodifiableSet());
            }
        );
    }

    public static List<PurchaseModel> getPurchases(UserModel userModel) {
        return TransactionUtils.inTransaction(
            (em, txn) ->  {
                TypedQuery<PurchaseModel> query = em.createQuery("SELECT p FROM PurchaseModel p WHERE p.buyer = :user ORDER BY p.timestamp DESC", PurchaseModel.class);
                query.setParameter("user", userModel);
                return query.getResultList();
            }
        );
    }
}
