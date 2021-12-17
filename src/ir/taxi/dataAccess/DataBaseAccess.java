package ir.taxi.dataAccess;

import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

@Data
public class DataBaseAccess {
  private SessionFactory sessionFactory ;

    public DataBaseAccess()  {
        sessionFactory = new Configuration().configure("ir/taxi/hibernate/hibernate.cfg.xml").buildSessionFactory();

    }

}
