package Model;

public class Client {
    private int clientId;
    private String name;
    private String surname;
    private String phoneNumber;

    public Client(int clientId, String name, String surname, String phoneNumber) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

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
