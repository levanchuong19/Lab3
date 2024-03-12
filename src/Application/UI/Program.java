/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import DataLayer.ProductDao.TourDao;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KhoaDang
 */
public class Program {

    public static void main(String[] args) {
        String productFile = "Tour.dat";
        TourDao pd = new TourDao();
        ArrayList<Tour> arrTour = new ArrayList<>();
        ArrayList<Hotel> arrHotel = new ArrayList<>();
        ArrayList<Booking> arrBooking = new ArrayList<>();
        pd.loadDataFromFile(arrTour, arrHotel, arrBooking, productFile);
        HotelManager hm = new HotelManager();
        BookingManager bm = new BookingManager();
        TourManager tm = new TourManager();
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        int choice;
        ops.add("Tour Management");
        ops.add("Hotel Management");
        ops.add("Booking Management");
        ops.add("Reporting and Analytics");
        ops.add("Store data to files");
        ops.add("Orther: Exit");
        do {
            System.out.println("----------- MAIN MENU -----------");
            choice = menu.int_getChoiceString(ops, 1, 7);
            switch (choice) {
                case 1:
                    tm.tourManager(arrTour, arrHotel, arrBooking);
                    break;
                case 2:
                    hm.hotelManager(arrTour, arrHotel, arrBooking);
                    break;
                case 3:
                    int choice2;
                    ArrayList<String> ops2 = new ArrayList<>();
                    Scanner sc = new Scanner(System.in);
                    ops2.add("Customer booking");
                    ops2.add("Booking manager");
                    ops2.add("Exit");
                    do {
                        boolean check = false;
                        choice2 = menu.int_getChoiceString(ops2, 1, 3);
                        switch (choice2) {
                            case 1:
                                bm.customerBookingManager(arrTour, arrHotel, arrBooking);
                                break;
                            case 2:
                                bm.bookingManager(arrTour, arrHotel, arrBooking);
                                break;
                            default:
                                System.out.println("Bye!");
                        }
                    } while (!(choice2 < 1 || choice2 > 2));
                    break;
                case 4:
                    bm.reportManager(arrTour, arrHotel, arrBooking);
                    break;
                case 5:
                    pd.saveDataFromFile(arrTour, arrHotel, arrBooking, productFile);
                    break;
              
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 6));
    }
}
