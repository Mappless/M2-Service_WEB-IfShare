package fr.uge.service_web.ifshare.not_shared.database.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@FunctionalInterface
public interface TransactionFunction<T> {
    T apply(EntityManager em, EntityTransaction txn);
}
