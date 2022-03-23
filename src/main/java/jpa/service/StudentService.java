package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentService implements StudentDAO {

    public StudentService() {
    }

    @Override
    public List<Student> getAllStudents()
    {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "From Student";
        Query query = session.createQuery(hql);
        query.getResultList();
        List<Student> students = query.getResultList();

        factory.close();
        session.close();

        return students;
    }

    @Override
    public Student getStudentByEmail(String sEmail)
    {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Student student = session.get(Student.class, sEmail); //get student by email

        factory.close();
        session.close();

        return student;
    }

    @Override
    public Boolean validateStudent(String sEmail, String sPassword) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        TypedQuery typedQuery = session.getNamedQuery("validatestudent");
        typedQuery.setParameter("email", sEmail);
        typedQuery.setParameter("password",sPassword);
        List<Student> students = typedQuery.getResultList();
        if (students.size() > 0)
        {
            return true;
        }

        factory.close();
        session.close();
        return false;
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, sEmail);
        List<Course> courses = student.getCourses();
        Course course = session.get(Course.class, cId);
        if (course == null) {
            System.out.println("The course doesn't exist! ");
        } else {
            courses.add(course);
            student.setCourses(courses);
        }

        transaction.commit();
        factory.close();
        session.close();
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Student student = session.get(Student.class, sEmail);
        List<Course> courses = student.getCourses();

        factory.close();
        session.close();
        return courses;
    }


}
