/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import BussinessLayer.Service.HotelService;
import java.util.ArrayList;

/**
 *
 * @author KhoaDang
 */
public class HotelManager {
    public void hotelManager(ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel, ArrayList<Booking> arrBooking) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        HotelService hs = new HotelService(arrHotel, arrTour);  
        int choice;
        ops.add("Add new hotel");
        ops.add("Update hotel");
        ops.add("Search hotel");
        ops.add("Remove hotel");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoiceString(ops, 1, 5);
            switch (choice) {
                case 1:
                    hs.addNewHotel();
                    break;
                case 2:
                    hs.updateHotel();
                    break;
                case 3:
                    hs.searchHotel();
                    break;
                case 4:
                    hs.removeHotel();
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 4));
    }
}
