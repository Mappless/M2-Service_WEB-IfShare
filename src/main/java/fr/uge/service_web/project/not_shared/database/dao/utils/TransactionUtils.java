package fr.uge.service_web.project.not_shared.database.dao.utils;

import fr.uge.service_web.project.not_shared.database.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

public class TransactionUtils {
    public static <T> T inTransaction(TransactionFunction<? extends T> function) {
        EntityManager em = DBConnection.getEntityManager();
        EntityTransaction txn = em.getTransaction();
        T result;

        try {
            txn.begin();
            result = function.apply(em, txn);
            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            throw e;
        } finally {
            em.close();
        }

        return result;
    }

    public static <T> void update(T model, Consumer<? super T> updateFunction) {
        TransactionUtils.inTransaction(
            (em, txn) -> {
                T persistentModel = em.merge(model);
                updateFunction.accept(persistentModel);
                return null;
            }
        );
    }

    public static <T> T update(T model) {
        return TransactionUtils.inTransaction(
            (em, txn) -> {
                return em.merge(model);
            }
        );
    }
}
