package fr.uge.service_web.project.not_shared.database.dao;

import fr.uge.service_web.project.not_shared.Offer;
import fr.uge.service_web.project.not_shared.database.DBConnection;
import fr.uge.service_web.project.not_shared.database.exception.FailedToRetrieveProductException;
import fr.uge.service_web.project.not_shared.database.model.OfferModel;
import fr.uge.service_web.project.not_shared.database.model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OfferDAO {
    private static Offer fromResultSet(ResultSet rs) {

    }

    public List<Offer> getByProductId(String id) throws SQLException {
        Connection connection = DBConnection.get();
        String query = "SELECT * FROM offer INNER JOIN person ON offer.seller_id = person.person_id "
                     + "INNER JOIN product ON offer.product_id = product.product_id WHERE product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(0, id);

        ResultSet rs = statement.executeQuery();

        if (!rs.next())
            throw new FailedToRetrieveProductException("Failed to retrieve product with ID " + id);

        return ProductModel.fromResultSet(rs);
    }
}
