package BusinessLogic.validatorsForOrders;

import DataAccess.ClientDAO;
import Model.Orders;

import javax.swing.*;

public class OrderClientIdValidator implements Validator<Orders>{
    private ClientDAO clientDAO;

    public OrderClientIdValidator() {
        this.clientDAO = new ClientDAO();
    }
    public void validate(Orders orders)
    {
        int clientId = orders.getClientID();
        if (clientDAO.findByIdForDeletion(clientId) == -1) {
            JOptionPane.showMessageDialog(null, "Client with id = " + clientId + " does not exist in the database!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Client with id = " + clientId + " does not exist in the database!");
        }
    }
}
