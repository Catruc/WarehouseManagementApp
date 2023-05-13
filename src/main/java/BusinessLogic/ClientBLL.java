package BusinessLogic;

import BusinessLogic.validatorsForClient.*;
import DataAccess.ClientDAO;
import DataAccess.GeneralDAO;
import Model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {

        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientIdValidator());
        validators.add(new ClientNameValidator());
        validators.add(new ClientSurnameValidator());
        validators.add(new ClientPhoneNumberValidator());
        clientDAO = new ClientDAO();
    }

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


    public void deleteClient(int id) {

        int cl = clientDAO.findByIdForDeletion(id);
        if (cl == -1) {
            JOptionPane.showMessageDialog(null, "The client with id =" + id + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        clientDAO.delete(id);
    }

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

    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll(Client.class);
        if (clients.isEmpty()) {
            throw new IllegalArgumentException("There are no clients in the database!");
        }
        return clients;
    }

    public Client findDeletion(int id)
    {
        Client cl = clientDAO.findWhatYouNeedById(id,Client.class);
        return cl;
    }

}
