package it.unitn.andone.assignment_4;


import jakarta.persistence.*;
import javax.ejb.*;
import java.util.Collection;

@Stateless
@Remote(CourseBeanIF.class)
public class CourseBean implements CourseBeanIF{

    @PersistenceContext(unitName="default")
    private EntityManager entityManager;

    @Override
    public String getName(int i) {
        Query q=entityManager.createQuery("Select * From COURSE where ID = "+i);
        Course c=(Course)(q.getSingleResult());
        return c.getName();
    }

    @Override
    public Course getCourse(int i) {
        Query q=entityManager.createQuery("Select * From COURSE where ID = "+i);
        Course c=(Course)(q.getSingleResult());
        return c;
    }

    @Override
    public Teacher getTeachers(int i) {
        Query q=entityManager.createQuery("Select * From COURSE where ID = "+i);
        Course c=(Course)(q.getSingleResult());
        return c.getTeacher();
    }
}
