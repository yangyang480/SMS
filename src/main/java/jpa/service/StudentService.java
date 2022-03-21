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
import java.util.Set;

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
        for (Student s : students)
        {
            System.out.println("Student email: " + s.getsEmail() + " Student name: " + s.getsName() + " Student password: " + s.getsPass());
        }
        factory.close();
        session.close();

        return students;
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT s FROM Student s WHERE s.sEmail = : email";
        TypedQuery typedQuery = session.createQuery(hql);
        typedQuery.setParameter("email", sEmail);
        List<Student> students = typedQuery.getResultList();
        for (Student s : students)
        {
            System.out.println("Student email: " + s.getsEmail() + " Student name: " + s.getsName() + " Student password: " + s.getsPass());
        }
        factory.close();
        session.close();

        return null;
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
        Set<Course> courses = student.getCourses();

        Course course = session.get(Course.class, cId);
        courses.add(course);
        // TODO check if course exists already that a student may have. Otherwise delete comment

        student.setCourses(courses);

        transaction.commit();
        factory.close();
        session.close();
    }

    @Override
    public void getStudentCourses(String sEmail) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Student student = session.get(Student.class, sEmail);
        Set<Course> courses = student.getCourses();

        if (courses.size() > 0)
        {
            System.out.println("# COURSE NAME  INSTRUCTOR NAME");
            for (Course c : courses)
            {
                System.out.println("Course ID: " + c.getcId() + " Course Name: " + c.getcName() + " Course Instructor: " + c.getcInstructorName());
            }
        }
        else {
            System.out.println("You have no course yet, please: ");
        }
        factory.close();
        session.close();
    }

}
