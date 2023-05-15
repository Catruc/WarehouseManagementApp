package DataAccess;

import Model.Orders;

public class OrderDAO extends GeneralDAO<Orders>{

    /**
     * OrderDAO attributes
     */

    public OrderDAO() {
        super("id");
    }
}
