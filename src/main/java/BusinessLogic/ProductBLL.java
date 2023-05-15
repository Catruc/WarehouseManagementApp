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

    /**
     * The list of validators for the product     *  DAO
     */
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    OrderBLL orderBLL = new OrderBLL();


    /**
     * The constructor of the class
     * It instantiates the validators list and the product DAO
     */

    public ProductBLL()
    {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductIdValidator());
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
        productDAO = new ProductDAO();
    }


    /**
     * The method that inserts a product into the database
     * @param product The product to be inserted
     */
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

    /**
     * The method that deletes a product from the database
     * @param id The id of the product to be deleted
     */


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

    /**
     * The method that updates a product from the database
     * @param product The product to be updated
     */


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

    /**
     * The method that finds a product by id
     * @return The product with the given id
     */


    public List<Product> findAllProducts() {
        List<Product> products = productDAO.findAll(Product.class);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("There are no products in the database!");
        }
        return products;
    }


    /**
     * The method that finds a product by id
     * @param id The id of the product to be found
     * @return The product with the given id
     */


    public Product findDeletion(int id)
    {
        Product pr = productDAO.findWhatYouNeedById(id,Product.class);
        return pr;
    }


}
