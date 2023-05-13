import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.OrderDAO;
import Presentation.Controller;
import Presentation.View;

public class Tester {

    public static void main(String[] args) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();
        View view = new View();
        Controller controller = new Controller(view,clientBLL,productBLL,orderBLL);
    }

}
