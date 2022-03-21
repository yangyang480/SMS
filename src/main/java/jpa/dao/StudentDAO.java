package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudentByEmail(String sEmail); //return type is student, we will use email to get student

    Boolean validateStudent(String sEmail, String sPassword); //if the email and password match the database will get return ture

    void registerStudentToCourse(String sEmail, int cId);

    void getStudentCourses(String sEmail);
}
