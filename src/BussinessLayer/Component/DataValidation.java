/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Component;

import Application.Utilities.DataInput;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author KhoaDang
 */
public class DataValidation {
    private final Scanner sc;
    private final SearchData sd;
    private final DataInput di;

    public DataValidation() {
        sc = new Scanner(System.in);
        sd = new SearchData();
        di = new DataInput();
    }
   public String inputTourID(ArrayList<Tour> arr) {
        String id = "";
        do {
            System.out.println("Enter id of tour: ");
            id = sc.nextLine().toUpperCase();
            if (sd.searchTourByID(arr, id) != null) {
                System.err.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.err.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
   public String inputHotelID(ArrayList<Hotel> arr) {
        String id = "";
        do {
            System.out.println("Enter id of hotel: ");
            id = sc.nextLine().toUpperCase();
            if (sd.searchHotelByID(arr, id) != null) {
                System.err.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.err.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
   public String inputNameUD(String msg, Tour tour){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return tour.getName();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the name");
            }else{
                return name;
            }
        } while (true);
    }
   public String inputNameHotelUD(String msg, Hotel hotel){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getName();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the name");
            }else{
                return name;
            }
        } while (true);
    }
   
   public String inputDestinationUD(String msg, Tour tour){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return tour.getDestination();
            }else{
                return name;
            }
        } while (true);
    }
   public String inputAmenitiesUD(String msg,Hotel hotel){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getAmenities();
            }else{
                return name;
            }
        } while (true);
    }
   public String inputPricingUD(String msg, Hotel hotel){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getPricing();
            }else{
                return name;
            }
        } while (true);
    }
   public LocalDateTime inputDateValidUD(String msg, String pattern, Tour tour){
        System.out.println(msg);
        boolean check = true;
        LocalDateTime date = null;
        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                String input = sc.nextLine();
                if(input.trim().isEmpty()){
                        date = tour.getDuration();
                        return date;
                }
                date = LocalDateTime.parse(input, dtf);
                check = false;
            } catch (DateTimeParseException e) {
                    System.out.println("Format date must be " + pattern);
                    check = true;
            }
        }
        return date;
    }
   public String inputDescriptionUD(String msg, Tour tour){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return tour.getDescription();
            }else{
                return name;
            }
        } while (true);
    }
   public double inputPriceDB(String msg, int x, int y, Tour tour) {
        System.out.println(msg);
        boolean check = true;
        double input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = tour.getPrice();
                    return input;
                }
                input = Double.parseDouble(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + (x + 1) + "to" + (y - 1));
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
   public String inputInclusionsUD(String msg, Tour tour){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return tour.getInclusions();
            }else{
                return name;
            }
        } while (true);
    }
   public String inputExclutionsUD(String msg, Tour tour){
        String name = "";
        System.out.println(msg);
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return tour.getExclutions();
            }else{
                return name;
            }
        } while (true);
    }
}
