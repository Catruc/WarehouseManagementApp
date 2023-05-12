package Model;

public class Client {
    private int clientID;
    private String name;
    private String surname;
    private int phoneNumber;

    public Client() {
        this.clientID = clientID;
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
        return clientID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.clientID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
