package BusinessLogic.validatorsForClient;

import Model.Client;

public class ClientNameValidator implements Validator<Client>{
    public void validate(Client c) {
        String name = c.getName();
        if(!name.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("The name must contain only letters!");
    }
}
