/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import BussinessLayer.Service.TourService;
import java.util.ArrayList;

/**
 *
 * @author KhoaDang
 */
public class TourManager {
    public void tourManager(ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel, ArrayList<Booking> arrBooking) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        TourService ts = new TourService(arrTour);
        int choice;
        ops.add("Add new tour");
        ops.add("Search tour");
        ops.add("Update tour");
        ops.add("Remove tour");
        ops.add("Display tour");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoiceString(ops, 1, 6);
            switch (choice) {
                case 1:
                    ts.addNewTour();
                    break;
                case 2:
                    ts.searchTour();
                    break;
                case 3:
                    ts.updateTour();
                    break;
                case 4:
                    ts.removeTour();
                    break;
                case 5:
                    ts.displayTour();
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 5));
    }
}
