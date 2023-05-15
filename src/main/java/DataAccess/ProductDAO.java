package DataAccess;

import Model.Product;

public class ProductDAO extends GeneralDAO<Product>{

    /**
     * ProductDAO attributes
     */
    public ProductDAO() {
        super("productId");
    }
}
