/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import BussinessLayer.Service.BookingService;
import java.util.ArrayList;

/**
 *
 * @author KhoaDang
 */
public class BookingManager {
    public void customerBookingManager(ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel, ArrayList<Booking> arrBooking) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        BookingService bs = new BookingService(arrBooking, arrTour, arrHotel);
        int choice;
        ops.add("Booking new tour and hotel");
        ops.add("Modification booking");
        ops.add("Cancallations");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoiceString(ops, 1, 4);
            switch (choice) {
                case 1:
                    bs.customerBooking();
                    break;
                case 2:
                    bs.updateTourAndHotel();
                    break;
                case 3:
                    bs.cancellationsTour();
                    break;
                
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 3));
    }
    public void bookingManager(ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel, ArrayList<Booking> arrBooking) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        BookingService bs = new BookingService(arrBooking, arrTour, arrHotel);
        int choice;
        ops.add("Display booked tours");
        ops.add("Display hotel reservations");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoiceString(ops, 1, 3);
            switch (choice) {
                case 1:
                    bs.displayBookedTour();
                    break;
                case 2:
                    bs.displayHotelReservations();
                    break;
                
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 2));
    }
    public void reportManager(ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel, ArrayList<Booking> arrBooking) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        BookingService bs = new BookingService(arrBooking, arrTour, arrHotel);
        int choice;
        ops.add("Display hotel status");
        ops.add("Display tour status");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoiceString(ops, 1, 3);
            switch (choice) {
                case 1:
                    bs.showRoomStatus();
                    break;
                case 2:
                    bs.showTourStatus();
                    break;
                
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 2));
    }
}
