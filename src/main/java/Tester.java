import BusinessLogic.ClientBLL;
import Presentation.Controller;
import Presentation.View;

public class Tester {

    public static void main(String[] args) {
        ClientBLL clientBLL = new ClientBLL();
        View view = new View();
        Controller controller = new Controller(view,clientBLL);
    }

}
