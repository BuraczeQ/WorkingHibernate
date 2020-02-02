package matyja.projects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class StudentManager {

    // == Session Factory ==
    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();


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

/*    public void removeStudent(int id){

    }

    Session session= sessionFactory.getCurrentSession();
    Student myStudent = session.get(Student.class, 3);

}*/
}
