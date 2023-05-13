package BusinessLogic.validatorsForProducts;

import Model.Product;

import javax.swing.*;

public class ProductIdValidator implements Validator<Product>{
    public void validate(Product product){
        if(product.getId() < 0){
            JOptionPane.showMessageDialog(null, "The id can't be negative!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The id can't be negative!");
        }
    }
}
