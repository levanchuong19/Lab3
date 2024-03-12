/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Component;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import java.util.ArrayList;

/**
 *
 * @author KhoaDang
 */
public class SearchData {
    public Tour searchTourByID(ArrayList<Tour> arr, String id){
        for (Tour tour : arr) {
            if(id.equals(tour.getTourId())){
                return tour;
            }
        }
        return null;
    }
    public Hotel searchHotelByID(ArrayList<Hotel> arr, String id){
        for (Hotel hotel : arr) {
            if(id.equals(hotel.getHotelID())){
                return hotel;
            }
        }
        return null;
    }
    public String searchSeats(Hotel hotel, String seats){
        ArrayList<String> arrSeats = hotel.getAvailableRoom();
        for (String arrSeat : arrSeats) {
            if(seats.equals(arrSeat)){
                return arrSeat;
            }
        }
        return null;
    }
    
    public Booking searchCusID(ArrayList<Booking> arr, String id){
        for (Booking booking : arr) {
            if(id.equals(booking.getCustomerID())){
                return booking;
            }
        }
        return null;
    }
}
