package BusinessLogic.validatorsForProducts;

import Model.Client;
import Model.Product;

public class ProductQuantityValidator implements Validator<Product>{
    public void validate(Product p) {
        int quantity = p.getQuantity();
        String quantityString = Integer.toString(quantity);
        if(!quantityString.matches("[0-9]+"))
            throw new IllegalArgumentException("The quantity must contain only numbers!");
    }
}
