import controller.RentController;
import controller.RentControllerImpl;

public class Main {
    public static void main(String[] args) {
        RentController rentController = new RentControllerImpl();
        rentController.handleRentRequest();
    }
}

