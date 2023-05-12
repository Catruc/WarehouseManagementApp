package BusinessLogic.validatorsForClient;

import Model.Client;

public class ClientSurnameValidator implements Validator<Client>{
    public void validate(Client c) {
        String surname = c.getSurname();
        if(!surname.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("The surname must contain only letters!");
    }
}
