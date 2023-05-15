package DataAccess;

import Model.Bill;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Orders;



public class BillDAO {

    /**
     * BillDAO attributes
     * @param LOGGER
     * @param INSERT_QUERY
     * @param SELECT_ALL_QUERY
     */

    protected final Logger LOGGER = Logger.getLogger(getClass().getName());
    private static final String INSERT_QUERY = "INSERT INTO Bill (clientId, orderId, quantity) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Bill";


    /**
     * Method that inserts a bill into the database
     * @param bill
     * @param order
     */
    public void insertBill(Bill bill, Orders order) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setInt(1, order.getClientID());
            statement.setInt(2, order.getId());
            statement.setInt(3, order.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Method that finds all the bills from the database
     * @return bills
     */

    public  List<Bill> findAllBills() {
        List<Bill> bills = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                int clientId = resultSet.getInt("clientId");
                int orderId = resultSet.getInt("orderId");
                int quantity = resultSet.getInt("quantity");
                Bill bill = new Bill(clientId, orderId, quantity);
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:findAllBills " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bills;
    }



}
