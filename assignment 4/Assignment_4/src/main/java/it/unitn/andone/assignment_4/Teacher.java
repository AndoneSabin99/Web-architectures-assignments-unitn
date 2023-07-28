package it.unitn.andone.assignment_4;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name ="TEACHER")
public class Teacher implements Serializable {
    static int count=0;

    private Course course;

    @Id
    @Column(name ="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sseq")
    @SequenceGenerator(name="Sseq", sequenceName="TEACHER_SEQUENCE", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURAME")
    private String surname;

    public Teacher(){}

    public Teacher(String name, String surname){
        this.id = count++;
        this.name = name;
        this.surname = surname;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) {this.surname = surname; }

    @Override
    public String toString() { return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + "]";}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher that = (Teacher) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @OneToOne(cascade={CascadeType.PERSIST}, mappedBy="teacher")
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
