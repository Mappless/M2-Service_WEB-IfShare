package fr.uge.service_web.ifshare.not_shared.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-persistence-unit");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
