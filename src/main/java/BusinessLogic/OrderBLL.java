package BusinessLogic;

import BusinessLogic.validatorsForOrders.*;
import DataAccess.OrderDAO;
import Model.Orders;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {

    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new OrderClientIdValidator());
        validators.add(new OrderIdValidator());
        validators.add(new OrderProductIdValidator());
        validators.add(new ProductStockValidator());
        orderDAO = new OrderDAO();
    }


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


    public void deleteOrder(int id) {

        int cl = orderDAO.findByIdForDeletion(id);
        if (cl == -1) {
            JOptionPane.showMessageDialog(null, "The order with id =" + id + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The order with id =" + id + " was not found!");
        }
        orderDAO.delete(id);
    }

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

    public List<Orders> findAllOrders() {
        List<Orders> orders = orderDAO.findAll(Orders.class);
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("There are no orders in the database!");
        }
        return orders;
    }

    public Orders findDeletion(int id)
    {
        Orders or = orderDAO.findWhatYouNeedById(id, Orders.class);
        return or;
    }


}
