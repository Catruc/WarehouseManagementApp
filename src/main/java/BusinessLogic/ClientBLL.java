package BusinessLogic;

import BusinessLogic.validatorsForClient.*;
import DataAccess.ClientDAO;
import DataAccess.GeneralDAO;
import Model.Client;
import Model.Orders;
import Presentation.View;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientBLL{

    /**
     * The list of validators for the client     *  DAO
     * The order BLL
     */
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;
    private OrderBLL orderBLL = new OrderBLL();


    /**
     * The constructor of the class
     * It instantiates the validators list and the client DAO
     */
    public ClientBLL() {

        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientIdValidator());
        validators.add(new ClientNameValidator());
        validators.add(new ClientSurnameValidator());
        validators.add(new ClientPhoneNumberValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * The method that inserts a client into the database
     * @param client The client to be inserted
     */
    public void insertClient(Client client) {
        int cl = clientDAO.findByIdForDeletion(client.getClientID());
        if (cl != -1) {
            JOptionPane.showMessageDialog(null, "The client with id =" + client.getClientID() + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client with id =" + client.getClientID() + " already exists!");
        }

        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.insert(client);
    }

    /**
     * The method that deletes a client from the database
     * @param id The id of the client to be deleted
     */
    public void deleteClient(int id) {

        List<Orders> clientOrders = orderBLL.findAllOrders();
        if (!clientOrders.isEmpty() && clientOrders.stream().anyMatch(o -> o.getClientID() == id)) {
            JOptionPane.showMessageDialog(null, "Cannot delete the client. There are active orders associated with the client.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method without deleting the client
        }

        int cl = clientDAO.findByIdForDeletion(id);
        if (cl == -1) {
            JOptionPane.showMessageDialog(null, "The client with id =" + id + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        clientDAO.delete(id);
    }

    /**
     * The method that updates a client from the database
     * @param client The client to be updated
     */
    public void updateClient(Client client) {

        int cl = clientDAO.findByIdForDeletion(client.getClientID());
        if (cl == -1) {
            JOptionPane.showMessageDialog(null, "The client"  + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client " +  " was not found!");
        }

        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.update(client);
    }

    /**
     * The method that finds a client by id
     * @return The client with the given id
     */
    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll(Client.class);
        if (clients.isEmpty()) {
            throw new IllegalArgumentException("There are no clients in the database!");
        }
        return clients;
    }

    /**
     * The method that finds a client by id
     * @param id The id of the client to be found
     * @return The client with the given id
     */
    public Client findDeletion(int id)
    {
        Client cl = clientDAO.findWhatYouNeedById(id,Client.class);
        return cl;
    }




}
