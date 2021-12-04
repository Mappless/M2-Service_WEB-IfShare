package fr.uge.service_web.ifshare.not_shared.database.dao;

import fr.uge.service_web.ifshare.not_shared.database.utils.TransactionUtils;
import fr.uge.service_web.ifshare.not_shared.database.model.OfferModel;
import fr.uge.service_web.ifshare.not_shared.database.model.PurchaseModel;
import fr.uge.service_web.ifshare.not_shared.database.model.UserModel;

public class PurchaseDAO {
    public static PurchaseModel addPurchase(UserModel buyer, OfferModel offer, int quantity) {
        PurchaseModel purchaseModel = new PurchaseModel(offer, buyer, quantity);

        TransactionUtils.inTransaction(
            (em, txn) -> {
                em.persist(purchaseModel);
                return null;
            }
        );

        return purchaseModel;
    }
}
