package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Column(name = "id", unique = true, nullable = false)
    private int cId;
    @Column(name = "name", length = 50, nullable = false)
    private String cName;
    @Column(name = "Instructor", length = 50, nullable = false)
    private String cInstructorName;
    private List<Student> students;

    public Course(){
        this.cId = 1;
        this.cName = "";
        this.cInstructorName = "";
        this.students = new ArrayList<>();
    }

    public Course(int cId, String cName, String cInstructorName){
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
        this.students = new ArrayList<>();
    }

    @Id
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
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
