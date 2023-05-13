import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Presentation.Controller;
import Presentation.View;

public class Tester {

    public static void main(String[] args) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        View view = new View();
        Controller controller = new Controller(view,clientBLL,productBLL);
    }

}
