package ir.taxi.model;

import ir.taxi.enumeration.TripStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
@ToString(callSuper = true)
@Data
@Entity
public class Driver extends Person{
    private String plaque;
    @JoinColumn(name = "car_fk")
    private int carId;
    @Column(name = "current_lat")
    private double currentLocationLat;
    @Column(name = "current_long")
    private double currentLocationLong;


    public Driver() {
    }

    public Driver(String name, String family, String username, String phoneNumber, long nationalCode,
                  Date birthDate, TripStatus status, String plaque, int carId) {
        super(name, family, username, phoneNumber, nationalCode, birthDate, status);
        this.plaque = plaque;
        this.carId = carId;
    }





}
