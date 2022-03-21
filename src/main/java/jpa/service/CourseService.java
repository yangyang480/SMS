package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.util.List;

public class CourseService implements CourseDAO {
    public CourseService() {
    }

    @Override
    public List<Course> getAllCourses() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "From Course";
        TypedQuery typedQuery = session.createQuery(hql);
        typedQuery.getResultList();
        List<Course> courses = typedQuery.getResultList();
        for (Course c : courses)
        {
            System.out.println("Course ID: " + c.getcId() + " Course name: " + c.getcName() + " Course Instructor: " + c.getcInstructorName());
        }
        factory.close();
        session.close();
        return null;
    }
}
