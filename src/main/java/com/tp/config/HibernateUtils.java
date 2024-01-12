package com.tp.config;

import com.tp.domain.Guest;
import com.tp.domain.Hotel;
import com.tp.domain.Reservation;
import com.tp.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static{//static bloklar uygulamma baslatildiginda ilk baslatilan kodelardir

        try {
            Configuration configuration=new Configuration().
                                        configure("hibernate.cfg.xml").
                                        addAnnotatedClass(Hotel.class).
                                        addAnnotatedClass(Room.class).
                                        addAnnotatedClass(Guest.class).
                                        addAnnotatedClass(Reservation.class);

            sessionFactory=configuration.buildSessionFactory();

        }catch (Exception e){
            System.err.println("Initialization of session factory is failed!!");
        }

    }

    //getter SF icin
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    //SF kapatalim
    public static void shutDown(){
        getSessionFactory().close();
    }

    //sessioni da kapatalim
    public static void closeSession(Session session){
        if (session!=null && session.isOpen()){
            session.close();
        }
    }
}
