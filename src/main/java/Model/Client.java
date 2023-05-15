package Model;

public class Client {

    /**
     * Client attributes
     * @param clientId
     * @param name
     * @param surname
     * @param phoneNumber
     */
    private int clientId;
    private String name;
    private String surname;
    private String phoneNumber;

    /**
     * Constructor
     * @param clientId
     * @param name
     * @param surname
     * @param phoneNumber
     */

    public Client(int clientId, String name, String surname, String phoneNumber) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructor
     */

    public Client() {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getClientID() {
        return clientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * toString method
     * @return
     */

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
