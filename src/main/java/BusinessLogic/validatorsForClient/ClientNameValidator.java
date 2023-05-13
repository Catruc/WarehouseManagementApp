package BusinessLogic.validatorsForClient;

import Model.Client;

import javax.swing.*;

public class ClientNameValidator implements Validator<Client>{
    public void validate(Client c) {
        String name = c.getName();
        if(!name.matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "The name must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The name must contain only letters!");
        }

    }
}
