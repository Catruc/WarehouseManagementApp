package BusinessLogic.validatorsForOrders;

import DataAccess.ProductDAO;
import Model.Orders;

import javax.swing.*;

public class OrderProductIdValidator implements Validator<Orders>{

    private ProductDAO productDAO;

    public OrderProductIdValidator() {
        this.productDAO = new ProductDAO();
    }

    public void validate(Orders orders)
    {
        int productId = orders.getProductID();
        if (productDAO.findByIdForDeletion(productId) == -1) {
            JOptionPane.showMessageDialog(null, "Product with id = " + productId + " does not exist in the database!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Product with id = " + productId + " does not exist in the database!");
        }
    }
}
