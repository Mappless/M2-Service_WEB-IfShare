package fr.uge.service_web.project.not_shared.database.dao;

import fr.uge.service_web.project.not_shared.Product;
import fr.uge.service_web.project.not_shared.database.DBConnection;
import fr.uge.service_web.project.not_shared.database.exception.FailedToRetrieveProductException;
import fr.uge.service_web.project.not_shared.database.model.ProductModel;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Product fromResultSet(ResultSet rs) throws SQLException, RemoteException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        return new Product(id, name, description);
    }

    public static List<Product> getAll() throws SQLException, RemoteException {
        Connection connection = DBConnection.get();
        List<Product> products = new ArrayList<>();

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM " + ProductModel.TABLE_NAME);

        while (rs.next())
            products.add(fromResultSet(rs));

        return products;
    }

    public static Product getByID(String id) throws SQLException, RemoteException {
        Connection connection = DBConnection.get();
        String query = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE id=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(0, id);

        ResultSet rs = statement.executeQuery();

        if (!rs.next())
            throw new FailedToRetrieveProductException("Failed to retrieve product with ID " + id);

        return fromResultSet(rs);
    }

    public static List<Product> getByName(String name) throws SQLException, RemoteException {
        Connection connection = DBConnection.get();
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE name LIKE '%?%'";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(0, name);

        ResultSet rs = statement.executeQuery();

        while (rs.next())
            products.add(fromResultSet(rs));

        return products;
    }

    public static boolean addProduct(Product product) throws SQLException, RemoteException {
        Connection connection = DBConnection.get();
        String query = "INSERT INTO " + ProductModel.TABLE_NAME + "(id, name, description) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(0, product.getId());
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());

        return statement.executeUpdate() == 1;
    }
}
