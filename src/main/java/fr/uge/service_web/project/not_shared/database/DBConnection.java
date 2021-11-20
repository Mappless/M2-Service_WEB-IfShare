package fr.uge.service_web.project.not_shared.database;

import fr.uge.service_web.project.not_shared.Config;
import fr.uge.service_web.project.not_shared.database.exception.FailedToConnectToDatabaseException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-persistence-unit");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
