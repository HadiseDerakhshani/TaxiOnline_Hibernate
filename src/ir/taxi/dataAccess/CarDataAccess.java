package ir.taxi.dataAccess;

import ir.taxi.model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDataAccess extends DataBaseAccess {

    private Session session;
    private Transaction transaction;

    public CarDataAccess() {
        super();
    }

    public List<Integer> saveGroupOfCar(List<Car> cars) throws SQLException {
        List<Integer> autoGeneratedKeys = new ArrayList<>();
        session =builderSession().openSession();
        transaction = session.beginTransaction();
        for (Car item : cars) {
            session.save(item);
            Query query = session.createQuery("Select max(id) from Car ");
            List list = query.list();
            Integer maxId = (Integer) list.get(0);
            autoGeneratedKeys.add(maxId);
        }
        transaction.commit();
        session.close();

        return autoGeneratedKeys;

    }

    public Integer saveNewCar(Car car) {
        session = builderSession().openSession();
        transaction = session.beginTransaction();
        session.save(car);
        Query query = session.createQuery("Select max(id) from Car ");
        List list = query.list();
        Integer autoId = (Integer) list.get(0);
        transaction.commit();
        session.close();
        return autoId;

    }
}
