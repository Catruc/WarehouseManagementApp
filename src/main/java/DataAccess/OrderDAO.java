package DataAccess;

import Model.Orders;

public class OrderDAO extends GeneralDAO<Orders>{

    public OrderDAO() {
        super("id");
    }
}
