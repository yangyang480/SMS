package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "validatestudent", query = "SELECT s FROM Student s WHERE s.sEmail = :email AND s.sPass = :password")
public class Student {
    private String sEmail;
    private String sName;
    private String sPass;
    private List<Course> courses;

    public Student() {
        this.sEmail = "";
        this.sName = "";
        this.sPass = "";
        this.courses = new ArrayList<>();
    }

    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.courses = new ArrayList<>();
    }

    public Student(String sEmail, String sName, String sPass, List<Course> courses){
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.courses = courses;
    }

    @Id
    @Column(name = "email", unique = true, length = 50, nullable = false)
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @Column(name = "name", length = 50, nullable = false)
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Column(name = "password", length = 50, nullable = false)
    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // We need EAGER to login otherwise will fail: 'LazyInitializationException: failed to lazily initialize a collection of role'
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}