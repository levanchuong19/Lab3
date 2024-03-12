/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;

/**
 *
 * @author KhoaDang
 */
public class Booking implements Serializable {

    private String customerID;
    private String customerName;
    private Tour bookingTour;
    private Hotel bookingHotel;
    private String room;

    public Booking(String customerID, String customerName, Tour bookingTour, Hotel bookingHotel, String room) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.bookingTour = bookingTour;
        this.bookingHotel = bookingHotel;
        this.room = room;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tour getBookingTour() {
        return bookingTour;
    }

    public void setBookingTour(Tour bookingTour) {
        this.bookingTour = bookingTour;
    }

    public Hotel getBookingHotel() {
        return bookingHotel;
    }

    public void setBookingHotel(Hotel bookingHotel) {
        this.bookingHotel = bookingHotel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return String.format("|%9s|%14s|%20s|%20s|%4s|\n", customerID, customerName, bookingTour.getDestination(), bookingHotel.getName(), room);
    }

}
