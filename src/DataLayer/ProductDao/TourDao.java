/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.ProductDao;

import BussinessLayer.Entity.Booking;
import BussinessLayer.Entity.Hotel;
import BussinessLayer.Entity.Tour;
import DataLayer.FileManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KhoaDang
 */
public class TourDao {
    private final FileManager fm;

    public TourDao() {
        fm = new FileManager();
    }

    /**
     * Using third party to serialize object load from file
     * 
     * @param wareHouseExport
     * @param warehouseImports
     * @param fName
     */
    public void loadDataFromFile(List<Tour> tour, List<Hotel> hotel,List<Booking> booking, String fName) {
        List<Object> objectsToSerialize = new ArrayList<>();
        fm.loadDataFromFile(objectsToSerialize, fName);
        for (Object object : objectsToSerialize) {
            if (object instanceof Tour) {
                tour.add((Tour) object);
            } else if (object instanceof Hotel) {
                hotel.add((Hotel) object);
            }else if (object instanceof Booking) {
                booking.add((Booking) object);
            }
        }
    }

    /**
     * Using third party to serialize object save to file
     * 
     * @param wareHouseExport
     * @param warehouseImports
     * @param fName
     */
    public void saveDataFromFile(List<Tour> tour, List<Hotel> hotel,List<Booking> booking, String fName) {
        List<Object> objectsToSerialize = new ArrayList<>();
        for (Tour whe : tour) {
            objectsToSerialize.add(whe);
        }
        for (Hotel whi : hotel) {
            objectsToSerialize.add(whi);
        }
        for (Booking booking1 : booking) {
            objectsToSerialize.add(booking1);
        }
        fm.saveDataToFile(objectsToSerialize, fName, "Save file successfull!");
    }
}
