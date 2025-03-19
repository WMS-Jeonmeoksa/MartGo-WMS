
import controller.LoginSignupController;
import controller.LoginSignupControllerImpl;
import controller.RentController;
import controller.RentControllerImpl;

public class Main {
    public static void main(String[] args) {
        LoginSignupController loginController = new LoginSignupControllerImpl();
        loginController.start();
    }
}

