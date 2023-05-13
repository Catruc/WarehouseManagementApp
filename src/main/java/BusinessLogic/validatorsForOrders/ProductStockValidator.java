package BusinessLogic.validatorsForOrders;

import DataAccess.ProductDAO;
import Model.Orders;
import Model.Product;

import javax.swing.*;

public class ProductStockValidator implements Validator<Orders>{
    private ProductDAO productDAO;

    public ProductStockValidator() {
        this.productDAO = new ProductDAO();
    }

    public void validate(Orders orders)
    {
        int productId = orders.getProductID();
        int quantity = orders.getQuantity();

        Product product = productDAO.findWhatYouNeedById(productId, Product.class);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Product with id = " + productId + " does not exist in the database!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Product with id = " + productId + " does not exist in the database!");
        }

        if (product.getStock() < quantity) {
            JOptionPane.showMessageDialog(null, "Insufficient stock for product with id = " + productId + "!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Insufficient stock for product with id = " + productId + "!");
        }
    }



}
