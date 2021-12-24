package ir.taxi.dataAccess;

import ir.taxi.enumeration.TripStatus;
import ir.taxi.model.Passenger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDataAccess extends DataBaseAccess {
    private Session session;
    private Transaction transaction;

    public PassengerDataAccess() {
        super();
    }

    public void saveGroupOfPassengers(List<Passenger> passengers) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        for (Passenger item : passengers) {
            session.save(item);
        }
        transaction.commit();
        session.close();

    }

    public String findPassengerByUsername(String username) {
        String result = "";
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select name from Passenger where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        result = (String) list.get(0);
        transaction.commit();
        session.close();
        return result;
    }

    public void saveNewPassenger(Passenger passenger) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        session.close();

    }

    public void increaseBalance(String username, int amount) {
        int increasedBalance = findBalanceByUserName(username) + amount;
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Passenger set balance=:b where name=:n");
        query.setParameter("b", increasedBalance);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public void decreaseBalance(String username, int price) {
        int decreasedBalance = findBalanceByUserName(username) - price;
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Passenger set balance=:b where name=:n");
        query.setParameter("b", decreasedBalance);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public int findBalanceByUserName(String username) {
        int findBalance = 0;
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select balance from Passenger where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        findBalance = (int) list.get(0);
        transaction.commit();
        session.close();
        return findBalance;
    }

    public Passenger getPassengerInformationByUsername(String username) throws SQLException {

        Passenger passenger = new Passenger();
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Passenger where name=:n");
        query.setParameter("n", username);
        passenger = (Passenger) query.list();
        transaction.commit();
        session.close();
        return passenger;

    }

    public int findPassengerIdByUsername(String username) {
        int id = 0;
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select id from Passenger where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        id = (int) list.get(0);
        transaction.commit();
        session.close();
        return id;
    }

    public List<Passenger> findPassengerById(int id) {
        List<Passenger> passengerInfo = new ArrayList<>();
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select name,family,phoneNumber,balance,status from Passenger where id=:i");
        query.setParameter("i", id);
        List<Passenger> list = query.list();
        for (Passenger item : list) {
            passengerInfo.add(item);
        }
        transaction.commit();
        session.close();
        return passengerInfo;

    }

    public TripStatus findStatusByUsername(String username) {
        TripStatus status = TripStatus.STOP;
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select status from Passenger where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        status = (TripStatus) list.get(0);
        transaction.commit();
        session.close();
        return status;


    }

    public void updateStatusToONGOINGByUsername(String username) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Passenger set status=:s where name=:n");
        query.setParameter("s", TripStatus.ONGOING);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public void updateStatusToSTOPByUsername(String username) throws SQLException {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Passenger set status=:s where name=:n");
        query.setParameter("s", TripStatus.STOP);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void updateStatusToSTOPById(int id) throws SQLException {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Passenger set status=:s where id=:i");
        query.setParameter("s", TripStatus.STOP);
        query.setParameter("i", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<Passenger> getListOfPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Passenger ");
        List<Passenger> list = query.list();
        for (Passenger item : list) {
            passengers.add(item);
        }
        transaction.commit();
        session.close();

        return passengers;


    }
}
