package ir.taxi.model;

import ir.taxi.enumeration.TripStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.sql.Date;

@ToString(callSuper = true)
@Data
@Entity
public class Passenger extends Person {

    private int balance;

    public Passenger(String name, String family, String username, String phoneNumber, long nationalCode, Date birthDate, TripStatus status, int balance) {
        super(name, family, username, phoneNumber, nationalCode, birthDate, status);
        this.balance = balance;
    }

    public Passenger() {
    }


}
