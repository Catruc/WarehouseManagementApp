package BusinessLogic.validatorsForClient;

import Model.Client;

public class ClientIdValidator implements Validator<Client> {
    public void validate(Client c) {
        if (c.getClientID() < 0) {
            throw new IllegalArgumentException("The client ID must be a positive number!");
        }
    }
}
