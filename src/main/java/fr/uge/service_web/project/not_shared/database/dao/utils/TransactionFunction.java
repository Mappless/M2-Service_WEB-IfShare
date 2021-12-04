package fr.uge.service_web.project.not_shared.database.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@FunctionalInterface
public interface TransactionFunction<T> {
    T apply(EntityManager em, EntityTransaction txn);
}
