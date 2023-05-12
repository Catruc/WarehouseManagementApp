package DataAccess;

import Model.Client;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO Clients (clientID, name, surname, phoneNumber)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM Clients where clientID = ?";

    private final static String editStatementString = "UPDATE Clients SET name = ?, surname = ?, phoneNumber = ? WHERE clientID = ?";

    private final static String deleteStatementString = "DELETE FROM Clients WHERE clientID = ?";

    public static int insertClient(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;

        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getClientID());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getSurname());
            insertStatement.setInt(4, client.getPhoneNumber());
            insertStatement.executeUpdate();
            insertedId = client.getClientID();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());

        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;

    }


    public static int deleteClient(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        int deletedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, client.getClientID());
            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                deletedId = client.getClientID();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deletedId;
    }

    public static int updateClient(Client client)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getSurname());
            updateStatement.setInt(3, client.getClientID());
            updateStatement.setInt(4, client.getPhoneNumber());
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                updatedId = client.getClientID();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }


    public static void main(String[] args) {
        // Creating a sample client
        Client client = new Client();
        client.setId(2);
        client.setName("John");
        client.setSurname("Doe");
        client.setPhoneNumber(3812);

        // Inserting the client into the database
        int insertedClientId = ClientDAO.insertClient(client);

        if (insertedClientId != -1) {
            System.out.println("Client inserted successfully with ID: " + insertedClientId);

            // Updating the client's surname in the database
            client.setPhoneNumber(3813); // Set the new surname
            int updatedClientId = ClientDAO.updateClient(client);

            if (updatedClientId != -1) {
                System.out.println("Client updated successfully with ID: " + updatedClientId);
            } else {
                System.out.println("Failed to update client in the database.");
            }

            // Deleting the client from the database
            //int deletedClientId = ClientDAO.deleteClient(client);

//            if (deletedClientId != -1) {
//                System.out.println("Client deleted successfully with ID: " + deletedClientId);
//            } else {
//                System.out.println("Failed to delete client from the database.");
//            }
        } else {
            System.out.println("Failed to insert client into the database.");
        }
    }


}
