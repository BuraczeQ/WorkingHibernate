package matyja.projects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;


public class StudentManager {

    // == Session Factory ==
    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

//DZIAŁA!!!
    public void addStudent() {

        Session session = sessionFactory.getCurrentSession();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter First Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter Email");
        String email = scanner.nextLine();


        Student student = new Student(firstName, lastName, email);
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        System.out.println("Adding new Student name: " + student.getFirstName() + " lastName = " + student.getLastName()
                + " email: " + student.getEmail());

        sessionFactory.close();
    }

    public void getStudent() {
        Session session = sessionFactory.getCurrentSession();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an id of a record that you want to remove... ");
        int id = scanner.nextInt();
        scanner.nextLine();

            // get Student entity using get() method
        session.beginTransaction();
        Student student = session.get(Student.class, id);

            System.out.println(student.getFirstName());
            System.out.println(student.getEmail());

            // commit transaction
        session.getTransaction().commit();
        sessionFactory.close();
        }
//DZIAŁA!!!
    public void deleteEmployee(int studId) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
           Student stu = session.get(Student.class, studId);
         /*   Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            scanner.nextLine();*/

            session.delete(stu);
            transaction.commit();
            System.out.println("deleted employee: "+stu.getFirstName());
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if(transaction != null) transaction.rollback();
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
    }

    //Działa!!!!
    public Student getStudentById(int studId){

        Session session = null;
        Student stu = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            String queryStr = "select emp from Employee emp";
            stu = session.get(Student.class, studId);

        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        System.out.println(stu.getFirstName());
        return stu;
    }



    }



