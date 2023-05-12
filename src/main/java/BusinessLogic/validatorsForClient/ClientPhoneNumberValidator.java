package BusinessLogic.validatorsForClient;

import Model.Client;

public class ClientPhoneNumberValidator implements Validator<Client>{
    public void validate(Client c) {
        int phoneNumber = c.getPhoneNumber();
        String phoneNumberString = String.valueOf(phoneNumber);
        if(!phoneNumberString.matches("[0-9]+"))
            throw new IllegalArgumentException("The phone number must contain only digits!");
    }
}
