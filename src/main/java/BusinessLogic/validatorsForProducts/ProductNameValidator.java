package BusinessLogic.validatorsForProducts;

import Model.Client;
import Model.Product;

public class ProductNameValidator implements Validator<Product>{
    public void validate(Product p) {
        String name = p.getName();
        if(!name.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("The name must contain only letters!");
    }

}
