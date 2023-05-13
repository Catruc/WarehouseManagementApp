package BusinessLogic.validatorsForProducts;

import Model.Product;

import javax.swing.*;

public class ProductPriceValidator implements Validator<Product>{

    public void validate(Product p) {
        double price = p.getPrice();
        String priceString = Double.toString(price);
        if (!priceString.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(null, "The price must be a valid decimal number!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The price must be a valid decimal number!");
        }
    }

}
