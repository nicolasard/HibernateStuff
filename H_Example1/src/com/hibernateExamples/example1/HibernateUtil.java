package com.hibernateExamples.example1;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
import com.websystique.hibernate.model.Student;
 
@SuppressWarnings("deprecation")
public class HibernateUtil {
     
    private static final SessionFactory sessionFactory;
     
    static{
        try{
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
 
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }   
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
     
}