package Presentation;

import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View {

    private JFrame welcomeFrame;

    private JLabel welcomeLabel;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;


    private JFrame clientFrame;

    private JLabel clientIDLabel;
    private JLabel clientNameLabel;
    private JLabel clientSurnameLabel;
    private JLabel clientPhoneNumberLabel;
    private JTextField clientIDTextField;
    private JTextField clientNameTextField;
    private JTextField clientSurnameTextField;
    private JTextField clientPhoneNumberTextField;
    private JButton clientInsertButton;
    private JButton deleteClientButton;
    private JButton updateClientButton;
    private JTable clientTable;
    private JButton backToWelcomeButton;

    private JFrame productFrame;

    private JLabel productIDLabel;
    private JLabel productNameLabel;
    private JLabel productPriceLabel;
    private JLabel productQuantityLabel;
    private JTextField productIDTextField;
    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productQuantityTextField;
    private JButton productInsertButton;
    private JButton deleteProductButton;
    private JButton updateProductButton;
    private JTable productTable;
    private JButton backToWelcomeButtonFromProduct;




    private JFrame orderFrame;

    private JLabel orderIDLabel;
    private JLabel orderClientIDLabel;
    private JLabel orderProductIDLabel;
    private JLabel orderQuantityLabel;
    private JTextField orderIDTextField;
    private JTextField orderClientIDTextField;
    private JTextField orderProductIDTextField;
    private JTextField orderQuantityTextField;

    private JButton orderInsertButton;
    private JButton deleteOrderButton;
    private JButton billOrderButton;
    private JTable orderTable;
    private JButton backToWelcomeButtonFromOrder;


    private JFrame billFrame;
    private JButton backToWelcomeButtonFromBill;
    private JTable billTable;


    public View() {
        welcomeFrame = new JFrame("Welcome");
        clientFrame = new JFrame("Client");
        productFrame = new JFrame("Product");
        orderFrame = new JFrame("Order");
        billFrame = new JFrame("Bill");

        welcomeFrame.setSize(1920, 1080);
        clientFrame.setSize(1920, 1080);
        productFrame.setSize(1920, 1080);
        orderFrame.setSize(1920, 1080);
        billFrame.setSize(1920, 1080);

        welcomeFrame.setLayout(null);
        clientFrame.setLayout(null);
        productFrame.setLayout(null);
        orderFrame.setLayout(null);
        billFrame.setLayout(null);

        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        welcomeFrame.getContentPane().setBackground(new Color(127, 255, 212)); // Aqua Blue color
        welcomeLabel = new JLabel("Welcome to the store!");
        welcomeLabel.setBounds(530, 100, 400, 100);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.BLUE);
        welcomeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.LIGHT_GRAY);
        welcomeLabel.setToolTipText("Welcome Message");
        welcomeFrame.add(welcomeLabel);

        clientButton = new JButton("CLIENT");
        clientButton.setBounds(250, 350, 400, 100);
        clientButton.setFont(new Font("Arial", Font.BOLD, 30));
        clientButton.setHorizontalAlignment(SwingConstants.CENTER);
        clientButton.setForeground(Color.BLUE);
        clientButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        clientButton.setOpaque(true);
        clientButton.setBackground(Color.LIGHT_GRAY);
        clientButton.setToolTipText("Client Button");
        welcomeFrame.add(clientButton);


        productButton = new JButton("PRODUCT");
        productButton.setBounds(800, 350, 400, 100);
        productButton.setFont(new Font("Arial", Font.BOLD, 30));
        productButton.setHorizontalAlignment(SwingConstants.CENTER);
        productButton.setForeground(Color.BLUE);
        productButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        productButton.setOpaque(true);
        productButton.setBackground(Color.LIGHT_GRAY);
        productButton.setToolTipText("Product Button");
        welcomeFrame.add(productButton);

        orderButton = new JButton("ORDER");
        orderButton.setBounds(525, 550, 400, 100);
        orderButton.setFont(new Font("Arial", Font.BOLD, 30));
        orderButton.setHorizontalAlignment(SwingConstants.CENTER);
        orderButton.setForeground(Color.BLUE);
        orderButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        orderButton.setOpaque(true);
        orderButton.setBackground(Color.LIGHT_GRAY);
        orderButton.setToolTipText("Order Button");
        welcomeFrame.add(orderButton);


        clientFrame.getContentPane().setBackground(new Color(127, 255, 212)); // Aqua Blue color
        clientIDLabel = new JLabel("ID:");
        clientIDLabel.setBounds(70, 60, 150, 50);
        clientIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientIDLabel);

        clientNameLabel = new JLabel("Name:");
        clientNameLabel.setBounds(70, 160, 150, 50);
        clientNameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientNameLabel);

        clientSurnameLabel = new JLabel("Surname:");
        clientSurnameLabel.setBounds(70, 260, 150, 50);
        clientSurnameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientSurnameLabel);

        clientPhoneNumberLabel = new JLabel("Phone Number:");
        clientPhoneNumberLabel.setBounds(70, 360, 250, 50);
        clientPhoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientPhoneNumberLabel);

        clientIDTextField = new JTextField();
        clientIDTextField.setBounds(250, 60, 100, 50);
        clientIDTextField.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientIDTextField);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(250, 160, 600, 50);
        clientNameTextField.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientNameTextField);

        clientSurnameTextField = new JTextField();
        clientSurnameTextField.setBounds(250, 260, 600, 50);
        clientSurnameTextField.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientSurnameTextField);

        clientPhoneNumberTextField = new JTextField();
        clientPhoneNumberTextField.setBounds(350, 360, 500, 50);
        clientPhoneNumberTextField.setFont(new Font("Arial", Font.BOLD, 30));
        clientFrame.add(clientPhoneNumberTextField);

        clientInsertButton = new JButton("INSERT");
        clientInsertButton.setBounds(1000, 30, 400, 100);
        clientInsertButton.setFont(new Font("Arial", Font.BOLD, 30));
        clientInsertButton.setHorizontalAlignment(SwingConstants.CENTER);
        clientInsertButton.setForeground(Color.BLUE);
        clientInsertButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        clientInsertButton.setOpaque(true);
        clientInsertButton.setToolTipText("Insert Button");
        clientFrame.add(clientInsertButton);

        deleteClientButton = new JButton("DELETE");
        deleteClientButton.setBounds(1000, 150, 400, 100);
        deleteClientButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteClientButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteClientButton.setForeground(Color.BLUE);
        deleteClientButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        deleteClientButton.setOpaque(true);
        deleteClientButton.setToolTipText("Delete Button");
        clientFrame.add(deleteClientButton);

        updateClientButton = new JButton("UPDATE");
        updateClientButton.setBounds(1000, 270, 400, 100);
        updateClientButton.setFont(new Font("Arial", Font.BOLD, 30));
        updateClientButton.setHorizontalAlignment(SwingConstants.CENTER);
        updateClientButton.setForeground(Color.BLUE);
        updateClientButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        updateClientButton.setOpaque(true);
        updateClientButton.setToolTipText("Update Button");
        clientFrame.add(updateClientButton);

        clientTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(clientTable);
        tableScrollPane.setBounds(50, 460, 800, 300);
        clientFrame.add(tableScrollPane);
        String[] columnNames = {"ID", "Name", "Surname"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        clientTable.setModel(tableModel);

        backToWelcomeButton = new JButton("BACK");
        backToWelcomeButton.setBounds(1000, 600, 400, 100);
        backToWelcomeButton.setFont(new Font("Arial", Font.BOLD, 30));
        backToWelcomeButton.setHorizontalAlignment(SwingConstants.CENTER);
        backToWelcomeButton.setForeground(Color.BLUE);
        backToWelcomeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backToWelcomeButton.setOpaque(true);
        backToWelcomeButton.setToolTipText("Back Button");
        clientFrame.add(backToWelcomeButton);


        //odrder frame

        orderFrame.getContentPane().setBackground(new Color(127, 255, 212)); // Aqua Blue color

        orderIDLabel = new JLabel("ID:");
        orderIDLabel.setBounds(70, 60, 150, 50);
        orderIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderIDLabel);

        orderClientIDLabel = new JLabel("Client ID:");
        orderClientIDLabel.setBounds(70, 160, 150, 50);
        orderClientIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderClientIDLabel);

        orderProductIDLabel = new JLabel("Product ID:");
        orderProductIDLabel.setBounds(70, 260, 180, 50);
        orderProductIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderProductIDLabel);

        orderQuantityLabel = new JLabel("Quantity:");
        orderQuantityLabel.setBounds(70, 360, 150, 50);
        orderQuantityLabel.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderQuantityLabel);

        orderIDTextField = new JTextField();
        orderIDTextField.setBounds(250, 60, 100, 50);
        orderIDTextField.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderIDTextField);

        orderClientIDTextField = new JTextField();
        orderClientIDTextField.setBounds(250, 160, 100, 50);
        orderClientIDTextField.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderClientIDTextField);

        orderProductIDTextField = new JTextField();
        orderProductIDTextField.setBounds(250, 260, 100, 50);
        orderProductIDTextField.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderProductIDTextField);

        orderQuantityTextField = new JTextField();
        orderQuantityTextField.setBounds(250, 360, 100, 50);
        orderQuantityTextField.setFont(new Font("Arial", Font.BOLD, 30));
        orderFrame.add(orderQuantityTextField);

        orderInsertButton = new JButton("INSERT");
        orderInsertButton.setBounds(1000, 30, 400, 100);
        orderInsertButton.setFont(new Font("Arial", Font.BOLD, 30));
        orderInsertButton.setHorizontalAlignment(SwingConstants.CENTER);
        orderInsertButton.setForeground(Color.BLUE);
        orderInsertButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        orderInsertButton.setOpaque(true);
        orderInsertButton.setToolTipText("Insert Button");
        orderFrame.add(orderInsertButton);

        deleteOrderButton = new JButton("DELETE");
        deleteOrderButton.setBounds(1000, 150, 400, 100);
        deleteOrderButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteOrderButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteOrderButton.setForeground(Color.BLUE);
        deleteOrderButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        deleteOrderButton.setOpaque(true);
        deleteOrderButton.setToolTipText("Delete Button");
        orderFrame.add(deleteOrderButton);

        billOrderButton = new JButton("Bill");
        billOrderButton.setBounds(1000, 270, 400, 100);
        billOrderButton.setFont(new Font("Arial", Font.BOLD, 30));
        billOrderButton.setHorizontalAlignment(SwingConstants.CENTER);
        billOrderButton.setForeground(Color.BLUE);
        billOrderButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        billOrderButton.setOpaque(true);
        billOrderButton.setToolTipText("Bill Button");
        orderFrame.add(billOrderButton);

        orderTable = new JTable();
        JScrollPane tableScrollPane2 = new JScrollPane(orderTable);
        tableScrollPane2.setBounds(50, 430, 800, 300);
        orderFrame.add(tableScrollPane2);
        String[] columnNames2 = {"ID", "Client ID", "Product ID", "Quantity"};
        DefaultTableModel tableModel2 = new DefaultTableModel(columnNames2, 0);
        orderTable.setModel(tableModel2);

        backToWelcomeButtonFromOrder = new JButton("BACK");
        backToWelcomeButtonFromOrder.setBounds(1000, 600, 400, 100);
        backToWelcomeButtonFromOrder.setFont(new Font("Arial", Font.BOLD, 30));
        backToWelcomeButtonFromOrder.setHorizontalAlignment(SwingConstants.CENTER);
        backToWelcomeButtonFromOrder.setForeground(Color.BLUE);
        backToWelcomeButtonFromOrder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backToWelcomeButtonFromOrder.setOpaque(true);
        backToWelcomeButtonFromOrder.setToolTipText("Back Button");
        orderFrame.add(backToWelcomeButtonFromOrder);

        //product frame

        productFrame.getContentPane().setBackground(new Color(127, 255, 212)); // Aqua Blue color

        productIDLabel = new JLabel("ID:");
        productIDLabel.setBounds(70, 60, 150, 50);
        productIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productIDLabel);

        productNameLabel = new JLabel("Name:");
        productNameLabel.setBounds(70, 160, 150, 50);
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productNameLabel);

        productPriceLabel = new JLabel("Price:");
        productPriceLabel.setBounds(70, 260, 150, 50);
        productPriceLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productPriceLabel);

        productQuantityLabel = new JLabel("Quantity:");
        productQuantityLabel.setBounds(70, 360, 150, 50);
        productQuantityLabel.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productQuantityLabel);

        productIDTextField = new JTextField();
        productIDTextField.setBounds(250, 60, 100, 50);
        productIDTextField.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productIDTextField);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(250, 160, 400, 50);
        productNameTextField.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productNameTextField);

        productPriceTextField = new JTextField();
        productPriceTextField.setBounds(250, 260, 100, 50);
        productPriceTextField.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productPriceTextField);

        productQuantityTextField = new JTextField();
        productQuantityTextField.setBounds(250, 360, 100, 50);
        productQuantityTextField.setFont(new Font("Arial", Font.BOLD, 30));
        productFrame.add(productQuantityTextField);

        productInsertButton = new JButton("INSERT");
        productInsertButton.setBounds(1000, 30, 400, 100);
        productInsertButton.setFont(new Font("Arial", Font.BOLD, 30));
        productInsertButton.setHorizontalAlignment(SwingConstants.CENTER);
        productInsertButton.setForeground(Color.BLUE);
        productInsertButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        productInsertButton.setOpaque(true);
        productInsertButton.setToolTipText("Insert Button");
        productFrame.add(productInsertButton);

        deleteProductButton = new JButton("DELETE");
        deleteProductButton.setBounds(1000, 150, 400, 100);
        deleteProductButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteProductButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteProductButton.setForeground(Color.BLUE);
        deleteProductButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        deleteProductButton.setOpaque(true);
        deleteProductButton.setToolTipText("Delete Button");
        productFrame.add(deleteProductButton);

        updateProductButton = new JButton("UPDATE");
        updateProductButton.setBounds(1000, 270, 400, 100);
        updateProductButton.setFont(new Font("Arial", Font.BOLD, 30));
        updateProductButton.setHorizontalAlignment(SwingConstants.CENTER);
        updateProductButton.setForeground(Color.BLUE);
        updateProductButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        updateProductButton.setOpaque(true);
        updateProductButton.setToolTipText("Update Button");
        productFrame.add(updateProductButton);

        productTable = new JTable();
        JScrollPane tableScrollPane3 = new JScrollPane(productTable);
        tableScrollPane3.setBounds(50, 430, 800, 300);
        productFrame.add(tableScrollPane3);
        String[] columnNames3 = {"ID", "Name", "Quantity", "Price"};
        DefaultTableModel tableModel3 = new DefaultTableModel(columnNames3, 0);
        productTable.setModel(tableModel3);

        backToWelcomeButtonFromProduct = new JButton("BACK");
        backToWelcomeButtonFromProduct.setBounds(1000, 600, 400, 100);
        backToWelcomeButtonFromProduct.setFont(new Font("Arial", Font.BOLD, 30));
        backToWelcomeButtonFromProduct.setHorizontalAlignment(SwingConstants.CENTER);
        backToWelcomeButtonFromProduct.setForeground(Color.BLUE);
        backToWelcomeButtonFromProduct.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backToWelcomeButtonFromProduct.setOpaque(true);
        backToWelcomeButtonFromProduct.setToolTipText("Back Button");
        productFrame.add(backToWelcomeButtonFromProduct);

        backToWelcomeButtonFromBill = new JButton("BACK");
        backToWelcomeButtonFromBill.setBounds(1000, 600, 400, 100);
        backToWelcomeButtonFromBill.setFont(new Font("Arial", Font.BOLD, 30));
        backToWelcomeButtonFromBill.setHorizontalAlignment(SwingConstants.CENTER);
        backToWelcomeButtonFromBill.setForeground(Color.BLUE);
        backToWelcomeButtonFromBill.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backToWelcomeButtonFromBill.setOpaque(true);
        backToWelcomeButtonFromBill.setToolTipText("Back Button");
        billFrame.add(backToWelcomeButtonFromBill);

        billTable = new JTable();
        JScrollPane tableScrollPane4 = new JScrollPane(billTable);
        tableScrollPane4.setBounds(50, 130, 800, 400);
        billFrame.add(tableScrollPane4);
        String[] columnNames4 = {"billId", "clientId", "productId", "quantity"};
        DefaultTableModel tableModel4 = new DefaultTableModel(columnNames4, 0);
        billTable.setModel(tableModel4);




        welcomeFrame.setVisible(true);
        clientFrame.setVisible(false);
        productFrame.setVisible(false);
        orderFrame.setVisible(false);
        billFrame.setVisible(false);


    }

    public JButton getClientButton() {
        return clientButton;
    }

    public JButton getProductButton() {
        return productButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getClientInsertButton() {
        return clientInsertButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getUpdateClientButton() {
        return updateClientButton;
    }

    public JFrame getWelcomeFrame() {
        return welcomeFrame;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JFrame getClientFrame() {
        return clientFrame;
    }

    public JLabel getClientIDLabel() {
        return clientIDLabel;
    }

    public JLabel getClientNameLabel() {
        return clientNameLabel;
    }

    public JLabel getClientSurnameLabel() {
        return clientSurnameLabel;
    }

    public JTextField getClientIDTextField() {
        return clientIDTextField;
    }

    public JTextField getClientNameTextField() {
        return clientNameTextField;
    }

    public JTextField getClientSurnameTextField() {
        return clientSurnameTextField;
    }

    public JTable getClientTable() {
        return clientTable;
    }

    public JFrame getProductFrame() {
        return productFrame;
    }

    public JFrame getOrderFrame() {
        return orderFrame;
    }

    public JButton getBackToWelcomeButton() {
        return backToWelcomeButton;
    }

    public JLabel getOrderIDLabel() {
        return orderIDLabel;
    }

    public JLabel getOrderClientIDLabel() {
        return orderClientIDLabel;
    }

    public JLabel getOrderProductIDLabel() {
        return orderProductIDLabel;
    }

    public JLabel getOrderQuantityLabel() {
        return orderQuantityLabel;
    }

    public JTextField getOrderIDTextField() {
        return orderIDTextField;
    }

    public JTextField getOrderClientIDTextField() {
        return orderClientIDTextField;
    }

    public JTextField getOrderProductIDTextField() {
        return orderProductIDTextField;
    }

    public JTextField getOrderQuantityTextField() {
        return orderQuantityTextField;
    }

    public JButton getOrderInsertButton() {
        return orderInsertButton;
    }

    public JButton getDeleteOrderButton() {
        return deleteOrderButton;
    }

    public JButton getBillOrderButton() {
        return billOrderButton;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public JButton getBackToWelcomeButtonFromOrder() {
        return backToWelcomeButtonFromOrder;
    }

    public JLabel getProductIDLabel() {
        return productIDLabel;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public JLabel getProductPriceLabel() {
        return productPriceLabel;
    }

    public JLabel getProductQuantityLabel() {
        return productQuantityLabel;
    }

    public JTextField getProductIDTextField() {
        return productIDTextField;
    }

    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    public JTextField getProductPriceTextField() {
        return productPriceTextField;
    }

    public JTextField getProductQuantityTextField() {
        return productQuantityTextField;
    }

    public JButton getProductInsertButton() {
        return productInsertButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public JButton getUpdateProductButton() {
        return updateProductButton;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public JButton getBackToWelcomeButtonFromProduct() {
        return backToWelcomeButtonFromProduct;
    }

    public JLabel getClientPhoneNumberLabel() {
        return clientPhoneNumberLabel;
    }

    public JTextField getClientPhoneNumberTextField() {
        return clientPhoneNumberTextField;
    }

    public JFrame getBillFrame() {
        return billFrame;
    }

    public JButton getBackToWelcomeButtonFromBill() {
        return backToWelcomeButtonFromBill;
    }

    public JTable getBillTable() {
        return billTable;
    }
}
