package Presentation;

import BusinessLogic.ClientBLL;
import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    private View view;
    private ClientBLL clientBLL;

    public Controller(View view,ClientBLL clientBLL) {
        this.view = view;
        this.clientBLL = new ClientBLL();
        initListeners();
    }


    public void updateClientTable() {
        List<Client> clients = clientBLL.findAllClients(); // Fetch the data from the database
        String[] columnNames = {"Id", "Name", "Surname", "PhoneNumber"}; // Specify column names

        // Prepare the data for the table
        Object[][] data = new Object[clients.size()][4];
        for (int i = 0; i < clients.size(); i++) {
            data[i][0] = clients.get(i).getClientID();
            data[i][1] = clients.get(i).getName();
            data[i][2] = clients.get(i).getSurname();
            data[i][3] = clients.get(i).getPhoneNumber();
        }

        // Create a new table model and set it for the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        view.getClientTable().setModel(model);
    }

    private void initListeners() {
        view.getClientButton().addActionListener(new ClientButtonListener());
        view.getProductButton().addActionListener(new ProductButtonListener());
        view.getOrderButton().addActionListener(new OrderButtonListener());
//        view.getClientInsertButton().addActionListener(new ClientInsertButtonListener());
//        view.getDeleteClientButton().addActionListener(new DeleteClientButtonListener());
//        view.getUpdateClientButton().addActionListener(new UpdateClientButtonListener());
        view.getBackToWelcomeButton().addActionListener(new BackToWelcomeButtonListener());
        view.getBackToWelcomeButtonFromOrder().addActionListener(new BackToWelcomeButtonListenerFromOrders());
        view.getBackToWelcomeButtonFromProduct().addActionListener(new BackToWelcomeButtonListenerFromProducts());
        view.getClientInsertButton().addActionListener(new ClientInsertButtonListener());
        view.getDeleteClientButton().addActionListener(new DeleteClientButtonListener());
        view.getUpdateClientButton().addActionListener(new UpdateClientButtonListener());
    }

    class ClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getClientFrame().setVisible(true);
            updateClientTable();
        }
    }

    class BackToWelcomeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getClientFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getOrderFrame().setVisible(true);
        }
    }

    class BackToWelcomeButtonListenerFromOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getOrderFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class BackToWelcomeButtonListenerFromProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getProductFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class ProductButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getProductFrame().setVisible(true);
        }
    }

    class ClientInsertButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                int id = Integer.parseInt(view.getClientIDTextField().getText());
                String name = view.getClientNameTextField().getText();
                String surname = view.getClientSurnameTextField().getText();
                String phoneNumber = view.getClientPhoneNumberTextField().getText();

                Client client = new Client(id, name, surname, phoneNumber);
                clientBLL.insertClient(client);

                updateClientTable();

            }catch (NumberFormatException ex)
            {
                System.out.println("The id must be an integer!");
            }catch (IllegalArgumentException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    class DeleteClientButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                int id = Integer.parseInt(view.getClientIDTextField().getText());
                clientBLL.deleteClient(id);

                updateClientTable();

            }catch (NumberFormatException ex)
            {
                System.out.println("The id must be an integer!");
            }catch (IllegalArgumentException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    class UpdateClientButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                int id = Integer.parseInt(view.getClientIDTextField().getText());
                Client existingClient = clientBLL.findDeletion(id);

                String name = view.getClientNameTextField().getText();
                if (!name.isEmpty()) {
                    existingClient.setName(name);
                }

                String surname = view.getClientSurnameTextField().getText();
                if (!surname.isEmpty()) {
                    existingClient.setSurname(surname);
                }

                String phoneNumber = view.getClientPhoneNumberTextField().getText();
                if (!phoneNumber.isEmpty()) {
                    existingClient.setPhoneNumber(phoneNumber);
                }

                clientBLL.updateClient(existingClient);

                updateClientTable();

            }catch (NumberFormatException ex)
            {
                System.out.println("The id must be an integer!");
            }catch (IllegalArgumentException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
