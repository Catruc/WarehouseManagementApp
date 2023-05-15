package DataAccess;

import Model.Client;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO extends GeneralDAO<Client> {

    /**
     * ClientDAO attributes
     */
    public ClientDAO() {
        super("clientId");
    }

}

