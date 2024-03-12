 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KhoaDang
 */
public class Tour implements Serializable {

    private String tourId;
    private String name;
    private String destination;
    private LocalDateTime duration;
    private String description;
    private double price;
    private String inclusions;
    private String exclutions;

    public Tour(String tourId, String name, String destination, LocalDateTime duration, String description, double price, String inclusions, String exclutions) {
        this.tourId = tourId;
        this.name = name;
        this.destination = destination;
        this.duration = duration;
        this.description = description;
        this.price = price;
        this.inclusions = inclusions;
        this.exclutions = exclutions;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getExclutions() {
        return exclutions;
    }

    public void setExclutions(String exclutions) {
        this.exclutions = exclutions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("(.{0,15})\\b");
        Matcher matcher = pattern.matcher(description);
        // Khởi tạo đối tượng Pattern, Matcher
        sb.append("-------------------------------------------------------------------------------------------------------------------------------\n");

        if (matcher.find()) {
            sb.append(String.format("|%9s|%17s|%15s|%20s|%15s|%12.2f|%15s|%15s|\n",
                    tourId,
                    name,
                    destination,
                    duration,
                    matcher.group(1),
                    price,
                    inclusions,
                    exclutions));
        }

        while (matcher.find()) {
            if (!matcher.group(1).equals("")) {
                sb.append(String.format("|%9s|%17s|%15s|%20s|%15s|%12s|%15s|%15s|\n",
                        "", "", "","",
                        matcher.group(1), "","","", ""));
            }
        }

        sb.append("-------------------------------------------------------------------------------------------------------------------------------\n");

        return sb.toString();
    }

}
