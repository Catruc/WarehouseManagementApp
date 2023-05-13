package BusinessLogic.validatorsForOrders;

import Model.Orders;

import javax.swing.*;

public class OrderIdValidator implements Validator<Orders>{

    public void validate(Orders orders){
        if(orders.getId() < 0){
            JOptionPane.showMessageDialog(null, "The id can't be negative!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The id can't be negative!");
        }
    }
}
