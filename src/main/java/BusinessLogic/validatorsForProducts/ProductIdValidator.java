package BusinessLogic.validatorsForProducts;

import Model.Product;

public class ProductIdValidator implements Validator<Product>{
    public void validate(Product product){
        if(product.getId() < 0){
            throw new IllegalArgumentException("The id can't be negative!");
        }
    }
}
