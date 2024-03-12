/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.UI.Menu;
import Application.Utilities.DataInput;
import BussinessLayer.Component.DataValidation;
import BussinessLayer.Component.SearchData;
import BussinessLayer.Entity.Tour;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KhoaDang
 */
public class TourService implements ITourService {

    private ArrayList<Tour> arrTour;
    private DataInput di;
    private DataValidation dv;
    private SearchData sd;
    private Menu mn;

    public TourService(ArrayList<Tour> arrTour) {
        this.arrTour = arrTour;
        di = new DataInput();
        dv = new DataValidation();
        sd = new SearchData();
        mn = new Menu();
    }

    @Override
    public void addNewTour() {
        String tourId;
        String name;
        String destination;
        LocalDateTime duration;
        String description;
        double price;
        String inclusions;
        String exclutions;
        boolean choice = true;
        while (choice) {
            tourId = dv.inputTourID(arrTour);
            name = di.inputStringPattern("Enter name of tour: ", "^[a-zA-Z\\s]+$");
            destination = di.inputStringNotEmpty("Enter tour destination: ");
            duration = di.inputDate("Enter duration time (must be hour:minus day-month-year): ", "HH:mm dd-MM-yyyy");
            description = di.inputStringNotEmpty("Enter tour description: ");
            price = di.inputDouble("Enter tour price($): ", 0, 10000000);
            inclusions = di.inputStringNotEmpty("Enter tour inclusions: ");
            exclutions = di.inputStringNotEmpty("Enter tour exclutions: ");
            arrTour.add(new Tour(tourId, name, destination, duration, description, price, inclusions, exclutions));
            choice = di.inputYN("Do you want to continue(Y/N): ");
        }
    }

    @Override
    public void searchTour() {
        ArrayList<String> ops = new ArrayList<>();
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the information you want to search for tour: ");
        ops.add("Search by destination");
        ops.add("Search by duration");
        ops.add("Search by price");
        ops.add("Exit");
        do {
            boolean check = false;
            choice = mn.int_getChoiceString(ops, 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("Enter destination you want to search");
                    String destination = sc.nextLine();
                    for (Tour tour : arrTour) {
                        if (tour.getDestination().contains(destination)) {
                            System.out.println(tour);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found id" + destination);
                    }
                    break;
                case 2:
                    LocalDateTime time1 = di.inputDate("Enter departure time from (must be hour:minus day-month-year): ", "HH:mm dd-MM-yyyy");
                    LocalDateTime time2 = di.inputDate("to: ", "HH:mm dd-MM-yyyy");

                    for (Tour tour : arrTour) {
                        if (tour.getDuration().isAfter(time1) && tour.getDuration().isBefore(time2)) {
                            System.out.println(tour);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found tour from " + time1.toString() + " to " + time2.toString() + "");
                    }
                    break;
                case 3:
                    double priceF = di.inputDouble("Please enter price from ", 0, 100000000);
                    double priceE = di.inputDouble(" to: ", 0, 100000000);
                    for (Tour tour : arrTour) {
                        if (tour.getPrice() >= priceF && tour.getPrice() <= priceE) {
                            System.out.println(tour);
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found price from " + priceF + " to " + priceE);
                    }
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 3));
    }

    @Override
    public void updateTour() {
        System.out.println("Enter id tour you want to update: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Tour tour = sd.searchTourByID(arrTour, id);
        String name;
        String destination;
        LocalDateTime duration;
        String description;
        double price;
        String inclusions;
        String exclutions;
        boolean check = true;
        boolean choice = true;
        if (tour != null) {
            System.out.println("Found! Here is tour: ");
            System.out.println(tour);
            name = dv.inputNameUD("Enter name of tour you want to update: ", tour);
            destination = dv.inputDestinationUD("Enter tour destination want to update: ", tour);
            duration = dv.inputDateValidUD("Enter duration time (must be hour:minus day-month-year) want to update: ", "HH:mm dd-MM-yyyy", tour);
            description = dv.inputDescriptionUD("Enter tour description want to update: ", tour);
            price = dv.inputPriceDB("Enter tour price($) want to update: ", 0, 10000000, tour);
            inclusions = dv.inputInclusionsUD("Enter tour inclusions want to update: ", tour);
            exclutions = dv.inputExclutionsUD("Enter tour exclutions want to update: ", tour);
            arrTour.set(arrTour.indexOf(tour), new Tour(id, name, destination, duration, description, price, inclusions, exclutions));
            System.out.println("Here is tour after update: ");
            System.out.println(tour);
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void removeTour() {
        System.out.println("Enter id of tour you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Tour tour = sd.searchTourByID(arrTour, id);
        boolean choice = true;
        if (tour != null) {
            System.out.println("Found! Here is product: ");
            System.out.println(tour);
            choice = di.inputYN("You really want to delete(Y/N): ");
            if (choice) {
                arrTour.remove(tour);
                System.out.println("Delete successfully!");
            }
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void displayTour() {
        System.out.printf("|%9s|%17s|%15s|%20s|%15s|%12s|%15s|%15s|\n", "TOUR ID",
                    "TOUR NAME",
                    "DESTINATION",
                    "DURATION",
                    "DESCRIPTION",
                    "PRICE",
                    "INCLUSIONS",
                    "EXCLUSION");
        for (Tour tour : arrTour) {
            System.out.println(tour);
        }
    }

}
