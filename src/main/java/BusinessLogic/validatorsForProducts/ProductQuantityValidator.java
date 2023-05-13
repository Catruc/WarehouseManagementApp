package BusinessLogic.validatorsForProducts;

import Model.Product;

import javax.swing.*;

public class ProductQuantityValidator implements Validator<Product>{
    public void validate(Product p) {
        int quantity = p.getStock();
        String quantityString = Integer.toString(quantity);
        if(!quantityString.matches("[0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "The quantity must contain only numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The quantity must contain only numbers!");
        }

    }
}
