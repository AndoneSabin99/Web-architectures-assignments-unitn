package it.unitn.andone.assignment_4;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name ="COURSE")
public class Course implements Serializable {

    static int count=0;

    private Collection<Student> students = new ArrayList<>();


    @Id
    @Column(name ="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sseq")
    @SequenceGenerator(name="Sseq", sequenceName="COURSE_SEQUENCE", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public Course(){}

    public Course(String name){
        this.id = count++;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }

    @Override
    public String toString() { return "Course [id=" + id + ", name=" + name + "]";}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }



    @ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, mappedBy="courses")
    @JoinTable(name="STUDENT_COURSE")
    public Collection<Student> getStudents() {
        return students;
    }
    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    private Teacher teacher;

    @OneToOne(cascade={CascadeType.PERSIST}, mappedBy="course")
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
