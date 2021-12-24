package ir.taxi.dataAccess;

import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Data
public class DataBaseAccess {
    private  static  SessionFactory sessionFactory;


        public static SessionFactory builderSession() {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            return sessionFactory;
        }

}
