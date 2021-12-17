package ir.taxi.model;

import ir.taxi.enumeration.TripStatus;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
@Data
@Entity
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String family;
    private String username;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="national_code")
    private long nationalCode;

    @Column(name="birth_date")
    private Date birthDate;

    private TripStatus status;

    public Person(String name, String family, String username, String phoneNumber, long nationalCode, Date birthDate, TripStatus status) {
        this.name = name;
        this.family = family;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
        this.status = status;
    }

    public Person() {
    }

    //region Description
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
    //endregion

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationalCode=" + nationalCode +
                ", birthDate=" + birthDate +
                ", status='" + status + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && nationalCode == person.nationalCode && Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nationalCode);
    }
}