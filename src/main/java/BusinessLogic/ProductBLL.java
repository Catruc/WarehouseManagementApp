package BusinessLogic;

import BusinessLogic.validatorsForProducts.Validator;

import BusinessLogic.validatorsForProducts.ProductIdValidator;
import BusinessLogic.validatorsForProducts.ProductNameValidator;
import BusinessLogic.validatorsForProducts.ProductPriceValidator;
import BusinessLogic.validatorsForProducts.ProductQuantityValidator;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    OrderBLL orderBLL = new OrderBLL();

    public ProductBLL()
    {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductIdValidator());
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
        productDAO = new ProductDAO();
    }


    public void insertProduct(Product product)
    {
        int pr = productDAO.findByIdForDeletion(product.getProductId());
        if(pr != -1)
        {
            JOptionPane.showMessageDialog(null, "The product with id =" + product.getProductId() + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The product with id =" + product.getProductId() + " already exists!");
        }

        for(Validator<Product> v : validators)
        {
            v.validate(product);
        }
        productDAO.insert(product);
    }


    public void deleteProduct(int id) {

        List<Orders> productOrders = orderBLL.findAllOrders();
        if (!productOrders.isEmpty() && productOrders.stream().anyMatch(o -> o.getProductID() == id)) {
            JOptionPane.showMessageDialog(null, "Cannot delete the product. There are active orders associated with the product.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method without deleting the client
        }

        int pr = productDAO.findByIdForDeletion(id);
        if (pr == -1) {
            JOptionPane.showMessageDialog(null, "The product with id =" + id + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The product with id =" + id + " was not found!");
        }
        productDAO.delete(id);
    }


    public void updateProduct(Product product) {

        int pr = productDAO.findByIdForDeletion(product.getProductId());
        if (pr == -1) {
            JOptionPane.showMessageDialog(null, "The product"  + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The product " +  " was not found!");
        }

        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        productDAO.update(product);
    }


    public List<Product> findAllProducts() {
        List<Product> products = productDAO.findAll(Product.class);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("There are no products in the database!");
        }
        return products;
    }


    public Product findDeletion(int id)
    {
        Product pr = productDAO.findWhatYouNeedById(id,Product.class);
        return pr;
    }


}
