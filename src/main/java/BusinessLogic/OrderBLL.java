package BusinessLogic;

import BusinessLogic.validatorsForOrders.*;
import DataAccess.OrderDAO;
import Model.Orders;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {

    /**
     * The list of validators for the order     *  DAO
     */
    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    /**
     * The constructor of the class
     * It instantiates the validators list and the order DAO
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new OrderClientIdValidator());
        validators.add(new OrderIdValidator());
        validators.add(new OrderProductIdValidator());
        validators.add(new ProductStockValidator());
        orderDAO = new OrderDAO();
    }


    /**
     * The method that inserts an order into the database
     * @param orders The order to be inserted
     */
    public void insertOrder(Orders orders) {
        int or = orderDAO.findByIdForDeletion(orders.getId());
        if (or != -1) {
            JOptionPane.showMessageDialog(null, "The order with id =" + orders.getId() + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The client with id =" + orders.getId() + " already exists!");
        }

        for (Validator<Orders> v : validators) {
            v.validate(orders);
        }
        orderDAO.insert(orders);
    }


    /**
     * The method that deletes an order from the database
     * @param id The id of the order to be deleted
     */
    public void deleteOrder(int id) {

        int cl = orderDAO.findByIdForDeletion(id);
        if (cl == -1) {
            JOptionPane.showMessageDialog(null, "The order with id =" + id + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The order with id =" + id + " was not found!");
        }
        orderDAO.delete(id);
    }


    /**
     * The method that updates an order from the database
     * @param orders The order to be updated
     */
    public void updateOrder(Orders orders) {

        int or = orderDAO.findByIdForDeletion(orders.getClientID());
        if (or == -1) {
            JOptionPane.showMessageDialog(null, "The order"  + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The order " +  " was not found!");
        }

        for (Validator<Orders> v : validators) {
            v.validate(orders);
        }
        orderDAO.update(orders);
    }

    /**
     * The method that finds all the orders from the database
     * @return The list of orders
     */
    public List<Orders> findAllOrders() {
        List<Orders> orders = orderDAO.findAll(Orders.class);
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("There are no orders in the database!");
        }
        return orders;
    }

    /**
     * The method that finds an order by id from the database
     * @param id The id of the order to be found
     * @return The order found
     */
    public Orders findDeletion(int id)
    {
        Orders or = orderDAO.findWhatYouNeedById(id, Orders.class);
        return or;
    }


}
