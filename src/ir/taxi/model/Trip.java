package ir.taxi.model;
import ir.taxi.enumeration.PayStatus;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="passenger_fk")
    private int passengerId;
    @Column(name="driver_fk")
    private int driverId;
    @Column(name="origin_lat")
    private double originLat;
    @Column(name="origin_long")
    private double originLong;
    @Column(name="destination_lat")
    private double destinationLat;
    @Column(name="destination_long")
    private double destinationLong;
    private int price;
    @Column(name="trip_date")
    private Date tripDate;
    @Column(name="pay_status")
    private PayStatus payStatus;

    public Trip(int passengerId, int driverId, double originLat, double originLong, double destinationLat, double destinationLong, int price, Date tripDate, PayStatus payStatus) {
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.originLat = originLat;
        this.originLong = originLong;
        this.destinationLat = destinationLat;
        this.destinationLong = destinationLong;
        this.price = price;
        this.tripDate = tripDate;
        this.payStatus = payStatus;
    }

    public Trip() {
    }


    @Override
    public String toString() {
        return "Ongoing Trip\n" +
                "Origin: " + originLat + ", " + originLong +"\n" +
                "Destination: " + destinationLat + ", " + destinationLong + "\n" +
                "Price: " + price + "\n" +
                "Date: " + tripDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.originLat, originLat) == 0 && Double.compare(trip.originLong, originLong) == 0 && Double.compare(trip.destinationLat, destinationLat) == 0 && Double.compare(trip.destinationLong, destinationLong) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(originLat, originLong, destinationLat, destinationLong);
    }
}
