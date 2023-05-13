package BusinessLogic.validatorsForProducts;

import Model.Product;

public class ProductPriceValidator implements Validator<Product>{

    public void validate(Product p) {
        double price = p.getPrice();
        String priceString = Double.toString(price);
        if (!priceString.matches("\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException("The price must be a valid decimal number!");
        }
    }

}
