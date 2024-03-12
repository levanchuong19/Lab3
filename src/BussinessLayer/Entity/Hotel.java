/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author KhoaDang
 */
public class Hotel implements Serializable {

    private String hotelID;
    private String name;
    private Tour location;
    private ArrayList<String> availableRoom;
    private String amenities;
    private String pricing;

    public Hotel(String hotelID, String name, Tour location, ArrayList<String> availableRoom, String amenities, String pricing) {
        this.hotelID = hotelID;
        this.name = name;
        this.location = location;
        this.availableRoom = availableRoom;
        this.amenities = amenities;
        this.pricing = pricing;
    }

    public Hotel(String hotelID, String name, Tour location, String amenities, String pricing) {
        this.hotelID = hotelID;
        this.name = name;
        this.location = location;
        this.availableRoom = new ArrayList<>(Arrays.asList(
                hotelID, "P01", "P02", "P03", "P04", "P11", "P12", "P13", "P14",
                "P21", "P22", "P23", "P24", "P31", "P32", "P33", "P34",
                "P41", "P42", "P43", "P44", "P51", "P52", "P53", "P54"
        ));
        this.amenities = amenities;
        this.pricing = pricing;
    }

    public Tour getLocation() {
        return location;
    }

    public void setLocation(Tour location) {
        this.location = location;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAvailableRoom() {
        return availableRoom;
    }

    public void setAvailableRoom(ArrayList<String> availableRoom) {
        this.availableRoom = availableRoom;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return String.format("|%9s|%20s|%20s|%20s|%20s|\n", hotelID, name, location.getDestination(), amenities, pricing);
    }

}
