package com.hibernateExamples.example1;

import java.util.List;
import org.hibernate.Session;
import com.websystique.hibernate.model.Student;
 
/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class Main {
 
    @SuppressWarnings("unused")
    public static void main(String[] args) {
 
        System.out.println("INICIANDO LA APLICACION");
 
        /*
         * Save few objects with hibernate
         */
        int studentId1 = saveStudent("Sam", "Disilva", "Maths");
        int studentId2 = saveStudent("Joshua", "Brill", "Science");
        int studentId3 = saveStudent("Peter", "Pan", "Physics");
        int studentId4 = saveStudent("Bill", "Laurent", "Maths");
 
        /*
         * Retrieve all saved objects
         */
        List<Student> students = getAllStudents();
        System.out.println("List of all persisted students >>>");
        for (Student student : students) {
            System.out.println("Persisted Student :" + student);
        }
 
        /*
         * Update an object
         */
        updateStudent(studentId4, "ARTS");
 
        /*
         * Deletes an object
         */
        deleteStudent(studentId2);
 
        /*
         * Retrieve all saved objects
         */
        List<Student> remaingStudents = getAllStudents();
        System.out.println("List of all remained persisted students >>>");
        for (Student student : remaingStudents) {
            System.out.println("Persisted Student :" + student);
        }
 
    }
 
    /**
     * This method saves a Student object in database
     */
    public int saveStudent(String firstName, String lastName, String section) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setSection(section);
 
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        int id = (Integer) session.save(student);
        session.getTransaction().commit();
        session.close();
        return id;
    }
 
    /**
     * This method returns list of all persisted Student objects/tuples from
     * database
     */
    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        @SuppressWarnings("unchecked")
        List<Student> employees = (List<Student>) session.createQuery(
                "FROM Student s ORDER BY s.firstName ASC").list();
 
        session.getTransaction().commit();
        session.close();
        return employees;
    }
 
    /**
     * This method updates a specific Student object
     */
    public void updateStudent(int id, String section) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        Student student = (Student) session.get(Student.class, id);
        student.setSection(section);
        //session.update(student);//No need to update manually as it will be updated automatically on transaction close.
        session.getTransaction().commit();
        session.close();
    }
 
    /**
     * This method deletes a specific Student object
     */
    public void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        Student student = (Student) session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }
}