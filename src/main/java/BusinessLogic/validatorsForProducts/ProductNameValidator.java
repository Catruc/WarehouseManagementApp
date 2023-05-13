package BusinessLogic.validatorsForProducts;

import Model.Client;
import Model.Product;

import javax.swing.*;

public class ProductNameValidator implements Validator<Product>{
    public void validate(Product p) {
        String name = p.getName();
        if(!name.matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "The name must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The name must contain only letters!");
        }

    }

}
