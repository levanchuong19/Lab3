/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.UI.Menu;
import Application.Utilities.DataInput;
import BussinessLayer.Component.DataValidation;
import BussinessLayer.Component.SearchData;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KhoaDang
 */
public class HotelService implements IHotelService {

    private ArrayList<Hotel> arrHotel;
    private DataInput di;
    private DataValidation dv;
    private SearchData sd;
    private Menu mn;
    private ArrayList<Tour> arrTour;

    public HotelService(ArrayList<Hotel> arrHotel, ArrayList<Tour> arrTour) {
        this.arrHotel = arrHotel;
        this.arrTour = arrTour;
        di = new DataInput();
        dv = new DataValidation();
        sd = new SearchData();
        mn = new Menu();
    }

    @Override
    public void addNewHotel() {
        String hotelID;
        String name;
        Tour location;
        String amenities;
        String pricing;
        boolean choice = true;
        while (choice) {
            hotelID = dv.inputHotelID(arrHotel);
            name = di.inputStringPattern("Enter name of Hotel: ", "^[a-zA-Z\\s]+$");
            location = (Tour) mn.ref_getChoice(arrTour, 1, arrTour.size());
            amenities = di.inputStringNotEmpty("Enter amenities hotel: ");
            pricing = di.inputStringNotEmpty("Enter pricing hotel: ");
            arrHotel.add(new Hotel(hotelID, name, location, amenities, pricing));
            choice = di.inputYN("Do you want to continue(Y/N): ");
        }
    }

    @Override
    public void updateHotel() {
        System.out.println("Enter id of hotel you want to update: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Hotel hotel = sd.searchHotelByID(arrHotel, id);
        String name;
        Tour location;
        ArrayList<String> availableRoom = null;
        String amenities;
        String pricing;
        if (hotel != null) {
            System.out.println("Found! Here is hotel: ");
            System.out.println(hotel);
            name = dv.inputNameHotelUD("Enter name of hotel you want to update: ", hotel);
            location = (Tour) mn.ref_getChoice(arrTour, 1, arrTour.size());
            int num = di.inputInteger("How many available room you want to add (0 if you don't want add)", 0, 5);
            if (num != 0) {
                for (int i = 0; i < num; i++) {
                    String room = di.inputStringPattern("Enter room", "^.{2}$");
                    availableRoom = arrHotel.get(arrHotel.indexOf(hotel)).getAvailableRoom();
                    availableRoom.add(room);
                }
            } else {
                availableRoom = arrHotel.get(arrHotel.indexOf(hotel)).getAvailableRoom();
            }
            amenities = dv.inputAmenitiesUD("Enter amenities of tour you want to update: ", hotel);
            pricing = dv.inputPricingUD("Enter pricing of tour you want to update: ", hotel);
            arrHotel.set(arrHotel.indexOf(hotel), new Hotel(id, name, location, availableRoom, amenities, pricing));
            System.out.println("Here is hotel after update: ");
            System.out.println(hotel);
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void searchHotel() {
        ArrayList<String> ops = new ArrayList<>();
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the information you want to search for hotel: ");
        ops.add("Search by location");
        ops.add("Search by amenities");
        ops.add("Search by room availability");
        ops.add("Exit");
        do {
            boolean check = false;
            choice = mn.int_getChoiceString(ops, 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("Enter location you want to search");
                    Tour location = (Tour) mn.ref_getChoice(arrTour, 1, arrTour.size());
                    for (Hotel hotel : arrHotel) {
                        if (hotel.getLocation().equals(location)) {
                            System.out.println(hotel);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found");
                    }
                    break;
                case 2:
                    System.out.println("Enter amenities you want to search");
                    String amenities = sc.nextLine();
                    for (Hotel hotel : arrHotel) {
                        if (hotel.getAmenities().contains(amenities)) {
                            System.out.println(hotel);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found amenities: " + amenities);
                    }
                    break;
                case 3:
                    System.out.println("Enter room available you want to search");
                    String room = sc.nextLine();
                    for (Hotel hotel : arrHotel) {
                        for (String string : hotel.getAvailableRoom()) {
                            if (string.equalsIgnoreCase(room)) {
                                System.out.println(hotel);
                            }
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found");
                    }
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 3));
    }

    @Override
    public void removeHotel() {
        System.out.println("Enter id of hotel you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Hotel hotel = sd.searchHotelByID(arrHotel, id);
        boolean choice = true;
        if (hotel != null) {
            System.out.println("Found! Here is product: ");
            System.out.println(hotel);
            choice = di.inputYN("You really want to delete(Y/N): ");
            if (choice) {
                arrHotel.remove(hotel);
                System.out.println("Delete successfully!");
            }
        } else {
            System.out.println("Not found!");
        }
    }

    public void createRoom(Hotel fi) {
        int numRows = (int) Math.ceil(((double) fi.getAvailableRoom().size()) / 4);
        int numCols = 4;

        String[][] flightMatrix = new String[numRows][numCols];

        int rowIndex = 0;
        int colIndex = 0;
        ArrayList<String> availableSeats = fi.getAvailableRoom();
        for (String str : availableSeats) {
            if (!str.equals(fi.getHotelID())) {
                flightMatrix[rowIndex][colIndex] = str;
                colIndex++;
                if (colIndex >= numCols) {
                    colIndex = 0;
                    rowIndex++;
                }
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("|              ROOM AT %9s HOTEL              |\n", fi.getName().toUpperCase());
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < numRows - 1; i++) {
            System.out.print("|");
            for (int j = 0; j < numCols; j++) {
                if (flightMatrix[i][j] != null && flightMatrix[i][j].endsWith("x")) {
                    System.out.printf("%8s    |", "Sold");
                } else if (flightMatrix[i][j] == null) {
                    break;
                } else {
                    System.out.printf("%8s    |", flightMatrix[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------");
    }
}
