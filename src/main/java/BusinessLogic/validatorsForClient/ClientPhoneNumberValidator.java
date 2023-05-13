package BusinessLogic.validatorsForClient;

import Model.Client;

import javax.swing.*;

public class ClientPhoneNumberValidator implements Validator<Client>{
    public void validate(Client c) {
        String phoneNumber = c.getPhoneNumber();
        if(!phoneNumber.matches("[0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "The phone number must contain only digits!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The phone number must contain only digits!");
        }

    }
}
