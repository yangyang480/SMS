package jpa.entitymodels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries(
    {
        @NamedQuery(name = "validatestudent", query = "SELECT s FROM Student s WHERE s.sEmail = :email AND s.sPass = :password")
    }
)
public class Student {
    private String sEmail;
    private String sName;
    private String sPass;
    private Set<Course> courses;

    public Student() {
        this.sEmail = "";
        this.sName = "";
        this.sPass = "";
        this.courses = new HashSet<Course>();
    }

    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.courses = new HashSet<Course>();
    }

    public Student(String sEmail, String sName, String sPass, Set<Course> courses){
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.courses = courses;
    }

    @Id
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_email", referencedColumnName = "sEmail"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "cId"))
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


}

/*@Entity
@Table
@NamedQueries(
    {
        @NamedQuery(name = "validatestudent", query = "SELECT s FROM Student s WHERE s.sEmail = :email AND s.sPass = :password")
        //@NamedQuery(name = "studentscourses", query = "SELECT c FROM Student s JOIN StudentCourse sc JOIN Course c WHERE s.sEmail = :email")
    }
)
public class Student {
    @Id
    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String sEmail;
    @Column(name = "name", length = 50, nullable = false)
    private String sName;
    @Column(name = "password", length = 50, nullable = false)
    private String sPass;

    @ManyToMany (fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "Student_Course",
            joinColumns = @JoinColumn(name = "Student_email"),
            inverseJoinColumns = @JoinColumn(name = "Course_id")

    )
    private List<Course> sCourses = new ArrayList<Course>();

    //instructors
    public Student() {
    }

    public Student(String sEmail, String sName, String sPass, List<Course> sCourses)
    {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }
}*/
