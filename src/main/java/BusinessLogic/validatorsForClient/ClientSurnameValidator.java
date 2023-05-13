package BusinessLogic.validatorsForClient;

import Model.Client;

import javax.swing.*;

public class ClientSurnameValidator implements Validator<Client>{
    public void validate(Client c) {
        String surname = c.getSurname();
        if(!surname.matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "The surname must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The surname must contain only letters!");
        }

    }
}
