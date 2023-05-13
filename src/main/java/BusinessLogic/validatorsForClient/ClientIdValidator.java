package BusinessLogic.validatorsForClient;

import Model.Client;

import javax.swing.*;

public class ClientIdValidator implements Validator<Client> {
    public void validate(Client c) {
        if (c.getClientID() < 0) {
            JOptionPane.showMessageDialog(null, "The client ID must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client ID must be a positive number!");
        }
    }
}
