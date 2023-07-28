package it.unitn.andone.assignment_4;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name ="STUDENT")
public class Student implements Serializable {
    static int count=0;
    private Collection<Course> courses = new ArrayList<>();
    @Id
    @Column(name ="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sseq")
    @SequenceGenerator(name="Sseq", sequenceName="STUDENT_SEQUENCE", allocationSize = 1)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURAME")
    private String surname;
    @Column(name = "MATRICULATION")
    private Integer matriculation;

    public Student(){}
    public Student(String name, String surname, int matriculation){
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.matriculation = matriculation;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) {this.surname = surname; }
    public int getMatriculation() { return matriculation; }
    public void setMatriculation(int matriculation) {this.matriculation = matriculation; }

    @Override
    public String toString() { return "Student [id=" + id + ", name=" + name + ", surname=" + surname +
            ", matriculation=" + matriculation + "]";}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (matriculation != null ? !matriculation.equals(that.matriculation) : that.matriculation != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (matriculation != null ? matriculation.hashCode() : 0);
        return result;
    }

    @ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER, mappedBy="students")
    @JoinTable(name="STUDENT_COURSE")
    public Collection<Course> getCourses() {
        return courses;
    }
    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

}
