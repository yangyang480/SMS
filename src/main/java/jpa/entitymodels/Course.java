package jpa.entitymodels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    private int cId;
    private String cName;
    private String cInstructorName;
    private Set<Student> students;

    public Course(){
        this.cId = 1;
        this.cName = "";
        this.cInstructorName = "";
        this.students = new HashSet<Student>();
    }

    public Course(String cName, String cInstructorName){
        this.cId = 1;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
        this.students = new HashSet<Student>();
    }

    public Course(int cId, String cName, String cInstructorName, Set<Student> students){
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
        this.students = students;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

    @ManyToMany(mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

/*@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int cId;
    @Column(name = "name", length = 50, nullable = false)
    private String cName;
    @Column(name = "Instructor", length = 50, nullable = false)
    private String cInstructorName;
    public Course(){}

    public Course(String cName, String cInstructorName)
    {
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }
}*/
