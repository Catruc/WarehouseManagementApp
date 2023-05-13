package BusinessLogic;

import BusinessLogic.validatorsForClient.*;
import DataAccess.ClientDAO;
import DataAccess.GeneralDAO;
import Model.Client;

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

    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        return cl;
    }


    public void insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.insert(client);
    }

    public void deleteClient(int id) {
        int cl = clientDAO.findByIdForDeletion(id);
        if (cl == -1) {
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        clientDAO.delete(id);
    }

    public void updateClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.update(client);
    }

    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll();
        if (clients.isEmpty()) {
            throw new IllegalArgumentException("There are no clients in the database!");
        }
        return clients;
    }

    public Client findDeletion(int id)
    {
        Client cl = clientDAO.findClientByIdForDeletion(id);
        return cl;
    }



}
