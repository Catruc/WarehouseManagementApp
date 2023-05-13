package BusinessLogic.validatorsForClient;

import Model.Client;

public class ClientPhoneNumberValidator implements Validator<Client>{
    public void validate(Client c) {
        String phoneNumber = c.getPhoneNumber();
        if(!phoneNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("The phone number must contain only digits!");
    }
}
