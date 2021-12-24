package ir.taxi.dataAccess;

import ir.taxi.enumeration.TripStatus;
import ir.taxi.model.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DriverDataAccess extends DataBaseAccess {
    private Session session;
    private Transaction transaction;

    public DriverDataAccess() {
        super();
    }

    public void saveGroupOfDrivers(List<Driver> drivers) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        for (Driver item : drivers) {
            session.save(item);
        }
        transaction.commit();
        session.close();

    }

    public String findDriverByUsername(String username) {
        String result = "";
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select name from Driver where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        result = (String) list.get(0);

        transaction.commit();
        session.close();
        return result;
    }

    public int findDriverIdByUsername(String username) {
        int driverId = 0;
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select id from Driver where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        driverId = (int) list.get(0);

        transaction.commit();
        session.close();
        return driverId;
    }

    public List<Driver> findDriverById(int id) {
        List<Driver> driverInfo = new ArrayList<>();
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select name,family,phoneNumber,plaque,status from Driver where id=:i");
        query.setParameter("i", id);
        List<Driver> list = query.list();
        for (Driver item : list) {
            driverInfo.add(item);
        }
        transaction.commit();
        session.close();
        return driverInfo;

    }

    public TripStatus findStatusByUsername(String username) {
        TripStatus status = TripStatus.WAIT;
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select status from Driver where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        status = (TripStatus) list.get(0);
        transaction.commit();
        session.close();
        return status;
    }

    public void UpdateDriverLocationByUsername(String username, Double[] point) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Driver set currentLocationLat=:late, currentLocationLong=:long where name=:n");
        query.setParameter("late", point[0]);
        query.setParameter("long", point[1]);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    public boolean findDriverLocationByUsername(String username) {
        double latitude = 0.0;
        double longitude = 0.0;

        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select currentLocationLat,currentLocationLong from Driver where name=:n");
        query.setParameter("n", username);
        List list = query.list();
        latitude = (double) list.get(0);
        longitude = (double) list.get(1);
        transaction.commit();
        session.close();

        if (latitude == 0.0 && longitude == 0.0)
            return true;

        return false;
    }

    public void updateDriverStatusToWaitByUsername(String username) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Driver set status=:s where name=:n");
        query.setParameter("s", TripStatus.WAIT);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public void updateDriverStatusToONGOINGByUsername(String username) {
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Driver set status=:s where name=:n");
        query.setParameter("s", TripStatus.ONGOING);
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public List<Driver> findDriverByWaitStatus() {
        List<Driver> drivers = new ArrayList<>();
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("Select id,name,family,plaque,currentLocationLat,currentLocationLong from Driver where status=:s");
        query.setParameter("s", "WAIT");
        List<Driver> list = query.list();
        for (Driver item : list) {
            drivers.add(item);
        }
        transaction.commit();
        session.close();

        return drivers;
    }

    public void saveNewDriver(Driver driver) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        session.save(driver);
        transaction.commit();
        session.close();


    }

    public Driver getDriverInformationByUsername(String username) {
        Driver driver = new Driver();
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery(" from Driver where name=:n");
        query.setParameter("n", username);
        driver = (Driver) query.list();
        transaction.commit();
        session.close();
        return driver;

    }

    public void updateDriverLocation(List<Double> destinationCoordinate, String username) {
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("update Driver set currentLocationLat=:late, currentLocationLong=:long where name=:n");
        query.setParameter("late", destinationCoordinate.get(0));
        query.setParameter("long", destinationCoordinate.get(1));
        query.setParameter("n", username);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    public List<Driver> getListOfDrivers() {
        List<Driver> drivers = new ArrayList<>();
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Driver ");
        List<Driver> list = query.list();
        for (Driver item : list) {
            drivers.add(item);
        }
        transaction.commit();
        session.close();
        return drivers;
    }
}