package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    private View view;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    public Controller(View view, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.view = view;
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.orderBLL = new OrderBLL();
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


    public void updateProductTable() {
        List<Product> products = productBLL.findAllProducts(); // Fetch the data from the database
        String[] columnNames = {"Id", "Name", "Quantity", "Price"}; // Specify column names

        // Prepare the data for the table
        Object[][] data = new Object[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            data[i][0] = products.get(i).getId();
            data[i][1] = products.get(i).getName();
            data[i][2] = products.get(i).getStock();
            data[i][3] = products.get(i).getPrice();
        }

        // Create a new table model and set it for the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        view.getProductTable().setModel(model);
    }


    public void updateOrderTable() {
        List<Orders> orders = orderBLL.findAllOrders(); // Fetch the data from the database
        String[] columnNames = {"Id", "ClientID", "ProductID", "Quantity"}; // Specify column names

        // Prepare the data for the table
        Object[][] data = new Object[orders.size()][4];
        for (int i = 0; i < orders.size(); i++) {
            data[i][0] = orders.get(i).getId();
            data[i][1] = orders.get(i).getClientID();
            data[i][2] = orders.get(i).getProductID();
            data[i][3] = orders.get(i).getQuantity();
        }

        // Create a new table model and set it for the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        view.getOrderTable().setModel(model);
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
        view.getProductInsertButton().addActionListener(new ProductInsertButtonListener());
        view.getDeleteProductButton().addActionListener(new DeleteProductButtonListener());
        view.getUpdateProductButton().addActionListener(new UpdateProductButtonListener());
        view.getOrderInsertButton().addActionListener(new OrderInsertButtonListener());
        view.getDeleteOrderButton().addActionListener(new DeleteOrderButtonListener());
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
            updateOrderTable();
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
            updateProductTable();
        }
    }

    class ClientInsertButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (view.getClientIDTextField().getText().equals("") || view.getClientNameTextField().getText().equals("") || view.getClientSurnameTextField().getText().equals("") || view.getClientPhoneNumberTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                int id = Integer.parseInt(view.getClientIDTextField().getText());
                String name = view.getClientNameTextField().getText();
                String surname = view.getClientSurnameTextField().getText();
                String phoneNumber = view.getClientPhoneNumberTextField().getText();

                Client client = new Client(id, name, surname, phoneNumber);
                clientBLL.insertClient(client);

                updateClientTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    class DeleteClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getClientIDTextField().getText());
                clientBLL.deleteClient(id);

                updateClientTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
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

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    class ProductInsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (view.getProductIDTextField().getText().equals("") || view.getProductNameTextField().getText().equals("") || view.getProductPriceTextField().getText().equals("") || view.getProductQuantityTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                int id = Integer.parseInt(view.getProductIDTextField().getText());
                String name = view.getProductNameTextField().getText();
                int quantity = Integer.parseInt(view.getProductQuantityTextField().getText());
                double price = Double.parseDouble(view.getProductPriceTextField().getText());

                Product product = new Product(id, name, quantity, price);
                productBLL.insertProduct(product);
                System.out.println("am ajuns aici");
                updateProductTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    class DeleteProductButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getProductIDTextField().getText());
                productBLL.deleteProduct(id);

                updateProductTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    class UpdateProductButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getProductIDTextField().getText());
                Product existingProduct = productBLL.findDeletion(id);

                String name = view.getProductNameTextField().getText();
                if (!name.isEmpty()) {
                    existingProduct.setName(name);
                }

                String quantityText = view.getProductQuantityTextField().getText();
                if (!quantityText.isEmpty()) {
                    int quantity = Integer.parseInt(quantityText);
                    if (quantity >= 0) {
                        existingProduct.setStock(quantity);
                    } else {
                        JOptionPane.showMessageDialog(null, "The quantity must be a non-negative integer!", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("The quantity must be a non-negative integer!");
                    }
                }


                String priceText = view.getProductPriceTextField().getText();
                if (!priceText.isEmpty()) {
                    double price = Double.parseDouble(priceText);
                    if (price > 0) {
                        existingProduct.setPrice(price);
                    } else {
                        JOptionPane.showMessageDialog(null, "The price must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("The price must be a positive number!");
                    }
                }

                productBLL.updateProduct(existingProduct);

                updateProductTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    class OrderInsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (view.getOrderIDTextField().getText().equals("") || view.getOrderClientIDTextField().getText().equals("") || view.getOrderProductIDTextField().getText().equals("") || view.getOrderQuantityTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                int id = Integer.parseInt(view.getOrderIDTextField().getText());
                int clientID = Integer.parseInt(view.getOrderClientIDTextField().getText());
                int productID = Integer.parseInt(view.getOrderProductIDTextField().getText());
                int quantity = Integer.parseInt(view.getOrderQuantityTextField().getText());

                Product product = productBLL.findDeletion(productID);

                Orders orders = new Orders(id, clientID, productID, quantity);
                orderBLL.insertOrder(orders);

                product.setStock(product.getStock() - quantity);
                productBLL.updateProduct(product);

                System.out.println("am ajuns aici");
                updateOrderTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    class DeleteOrderButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getOrderIDTextField().getText());
                orderBLL.deleteOrder(id);

                updateOrderTable();

            } catch (NumberFormatException ex) {
                System.out.println("The id must be an integer!");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }




}
