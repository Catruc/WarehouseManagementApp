package Model;

public class Product {

    /**
     * Product attributes
     * @param productId
     * @param name
     * @param stock
     * @param price
     */
    private int productId;
    private String name;
    private int stock;
    private double price;

    /**
     * Constructor
     * @param productId
     * @param name
     * @param stock
     * @param price
     */

    public Product(int productId, String name, int stock, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.productId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
