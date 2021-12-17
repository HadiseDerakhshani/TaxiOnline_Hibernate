package ir.taxi.dataAccess;

import ir.taxi.enumeration.PayStatus;
import ir.taxi.enumeration.TripStatus;
import ir.taxi.model.Driver;
import ir.taxi.model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TripDataAccess extends DataBaseAccess{
    private Session session ;
    private Transaction transaction;

    public TripDataAccess()  {
        super();
    }

    public PayStatus findPayStatusByDriverId(int id)  {
        PayStatus payStatus = null;
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query query = session.createQuery("Select payStatus from Trip where driverId=:d");
        query.setParameter("d",id);
        List list = query.list();
        payStatus= (PayStatus) list.get(0);
        transaction.commit();
        session.close();
        return payStatus;


    }
    public int findPassengerIdByDriverId(int id) {
        int foundId = 0;
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query query = session.createQuery("Select passengerId from Trip where driverId=:d");
        query.setParameter("d",id);
        List list = query.list();
        foundId=(int)list.get(0);
        transaction.commit();
        session.close();
        return foundId;
    }
    public void updatePayStatusAfterPaying(int id) {
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query query=session.createQuery("update Trip set payStatus=:s where driverId=:d");
        query.setParameter("s",PayStatus.PAYED);
        query.setParameter("d",id);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }
    public List<Double> findDestinationCoordinationById(int id) {
        List<Double> coordination = new ArrayList<Double>();
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query query = session.createQuery("Select destinationLat,destinationLong from Trip where driverId=:d");
        query.setParameter("d",id);
        List list = query.list();
        coordination.add((double)list.get(0));
        coordination.add((double)list.get(1));
        transaction.commit();
        session.close();

        return coordination;
    }

    public void saveTrip(Trip trip){
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        session.save(trip);
        transaction.commit();
        session.close();

    }

    public List<Trip> getOngoingTravels()  {
        List<Trip>ongoingTrips = new ArrayList<>();
        session=getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query query = session.createQuery("Select passengerId,driverId,originLat,originLong,destinationLat,destinationLong," +
                "price,tripDate from Trip where payStatus=:s or payStatus=:s1");
        query.setParameter("s","CASH");
        query.setParameter("s","ACCOUNT");
        List<Trip> list = query.list();
        for (Trip item : list) {
         ongoingTrips.add(item);
        }
        transaction.commit();
        session.close();
            return ongoingTrips;

    }
}
