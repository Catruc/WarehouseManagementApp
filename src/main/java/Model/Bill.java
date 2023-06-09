package Model;

import java.lang.reflect.Constructor;

/**
 * Bill attributes
 * @param clientId
 * @param orderId
 * @param quantity
 */
public record Bill(int clientId, int orderId, int quantity) {
    public Bill(int clientId, int orderId, int quantity) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.quantity = quantity;
    }
}
