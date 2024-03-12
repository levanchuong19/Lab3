/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.UI.Menu;
import Application.Utilities.DataInput;
import BussinessLayer.Component.DataValidation;
import BussinessLayer.Component.SearchData;
import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author KhoaDang
 */
public class BookingService implements IBookingService {

    private ArrayList<Booking> arrBooking;
    private DataInput di;
    private DataValidation dv;
    private SearchData sd;
    private Menu mn;
    private ArrayList<Tour> arrTour = new ArrayList<>();
    private ArrayList<Hotel> arrHotel = new ArrayList<>();
    private HotelService hs;

    public BookingService(ArrayList<Booking> arrBooking, ArrayList<Tour> arrTour, ArrayList<Hotel> arrHotel) {
        this.arrBooking = arrBooking;
        this.arrTour = arrTour;
        this.arrHotel = arrHotel;
        di = new DataInput();
        dv = new DataValidation();
        sd = new SearchData();
        mn = new Menu();
        hs = new HotelService(arrHotel, arrTour);
    }

    @Override
    public void customerBooking() {
        String customerID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();;
        String customerName = di.inputStringCanBlank("Enter name: ");
        Tour bookingTour = (Tour) mn.ref_getChoice(arrTour, 1, arrTour.size());
        ArrayList<Hotel> arrTemp = new ArrayList<>();
        for (Hotel hotel : arrHotel) {
            if (hotel.getLocation().equals(bookingTour)) {
                arrTemp.add(hotel);
            }
        }
        Hotel bookingHotel = (Hotel) mn.ref_getChoice(arrTemp, 1, arrTemp.size());
        hs.createRoom(bookingHotel);
        String room = null;
        boolean check = true;
        while (check) {
            String seats = di.inputStringPattern("Please choice a seat", "^.{3}$").toUpperCase();
            String checkSeats = sd.searchSeats(bookingHotel, seats);
            if (checkSeats != null) {
                bookingHotel.getAvailableRoom().set(bookingHotel.getAvailableRoom().indexOf(checkSeats), checkSeats + "x");
                room = seats;
                System.out.println("Reservation successful!");
                check = false;
            } else {
                System.out.println("Please choose an available seat");
                check = true;
            }
        }
        arrBooking.add(new Booking(customerID, customerName, bookingTour, bookingHotel, room));
    }

    @Override
    public void displayBookedTour() {
        System.out.printf("|%9s|%14s|%20s|%20s|%4s|\n", "CUSTOMERID", "CUSTOMER NAME", "DESTINATION", "LOCATION", "ROOM");
        for (Booking booking : arrBooking) {
            System.out.println(booking);
        }
    }

    @Override
    public void displayHotelReservations() {
        System.out.printf("|%9s|%20s|%20s|%20s|%20s|\n", "HOTEL ID", "HOTEL NAME", "LOCATION", "AMENITIES", "PRICING");
        for (Hotel hotel : arrHotel) {
            System.out.println(hotel);
            hs.createRoom(hotel);
        }
    }

    @Override
    public void updateTourAndHotel() {
        System.out.println("Enter your id to update: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Booking booking = sd.searchCusID(arrBooking, id);
        String name;
        Tour location;
        ArrayList<String> availableRoom = null;
        String amenities;
        String pricing;
        if (booking != null) {
            System.out.println("Found! Here is your booking: ");
            System.out.println(booking);
            System.out.println("Please choice booking new tour");
            Tour bookingTour = (Tour) mn.ref_getChoice(arrTour, 1, arrTour.size());
            ArrayList<Hotel> arrTemp = new ArrayList<>();
            for (Hotel hotel : arrHotel) {
                if (hotel.getLocation().equals(bookingTour)) {
                    arrTemp.add(hotel);
                }
            }
            Hotel bookingHotel = (Hotel) mn.ref_getChoice(arrTemp, 1, arrTemp.size());
            hs.createRoom(bookingHotel);
            String room = null;
            boolean check = true;
            while (check) {
                String seats = di.inputStringPattern("Please choice a seat", "^.{3}$").toUpperCase();
                String checkSeats = sd.searchSeats(bookingHotel, room);
                if (checkSeats != null) {
                    bookingHotel.getAvailableRoom().set(bookingHotel.getAvailableRoom().indexOf(checkSeats), checkSeats + "x");
                    booking.getBookingHotel().getAvailableRoom().set(booking.getBookingHotel().getAvailableRoom().indexOf(checkSeats), checkSeats.substring(0, 2));
                    room = seats;
                    System.out.println("Reservation successful!");
                    check = false;
                } else {
                    System.out.println("Please choose an available seat");
                    check = true;
                }
            }
            arrBooking.set(arrBooking.indexOf(booking), new Booking(id, booking.getCustomerName(), bookingTour, bookingHotel, room));
            System.out.println("Here is hotel after update: ");
            System.out.println(booking);
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void cancellationsTour() {
        System.out.println("Enter your id: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Booking booking = sd.searchCusID(arrBooking, id);
        boolean choice = true;
        if (booking != null) {
            System.out.println("Found! Here is product: ");
            System.out.println(booking);
            choice = di.inputYN("You really want to delete(Y/N): ");
            if (choice) {
                arrBooking.remove(booking);
                System.out.println("Delete successfully!");
            }
        } else {
            System.out.println("Not found!");
        }
    }

    public void showRoomStatus() {
        System.out.printf("|%9s|%20s|%20s|%20s|%20s|\n", "HOTEL ID", "HOTEL NAME", "LOCATION", "AMENITIES", "PRICING");
        for (Hotel hotel : arrHotel) {
            int count = 0;
            for (int i = 0; i < hotel.getAvailableRoom().size(); i++) {
                if (!hotel.getAvailableRoom().get(i).equals(hotel.getHotelID()) && hotel.getAvailableRoom().get(i).endsWith("x")) {
                    count++;
                }
            }
            String result = hotel.toString() + "NO ROOM BOOKED: "+count;
            System.out.println(result);
        }
    }

    public void showTourStatus() {
        System.out.printf("|%9s|%17s|%15s|%20s|%15s|%12s|%15s|%15s|\n", "TOUR ID",
                    "TOUR NAME",
                    "DESTINATION",
                    "DURATION",
                    "DESCRIPTION",
                    "PRICE",
                    "INCLUSIONS",
                    "EXCLUSION");
        for (Tour tour : arrTour) {
            int count = 0;
            for (Booking booking : arrBooking) {
                if (booking.getBookingTour().equals(tour)) {
                    count++;
                }
            }
            System.out.println(tour + "NO TOUR BOOKED: " + count + "");
        }
    }
}
