package view;

public interface RentView {

    int displayMenu();
    int getWareHouseChoice();
    String getSectorChoice(int wareHouse);
    int getRentPeriod();
    void displaySelection(int wareHouse, String sectorName, int month, int rentPrice);
    int confirmSelection();
    String getStartDate();
    void rentEnd();
}
