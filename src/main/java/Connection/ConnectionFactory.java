package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/Shop";
    private static final String USER = "root";
    private static final String PASS = "";

    private static ConnectionFactory singleInstance = new ConnectionFactory();


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }


    public static Connection getConnection() {
        return singleInstance.createConnection();
    }


    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DBURL, USER, PASS);
            Statement stn= connection.createStatement();
            ResultSet r= stn.executeQuery("SELECT * FROM Test");
            while(r.next())
            {
                String columnt1= r.getString("id");
                String column2= r.getString("nume");
                System.out.println(columnt1+" "+column2);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
