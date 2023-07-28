package it.unitn.andone.assignment_4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
@Remote(StudentBeanIF.class)
public class StudentBean implements StudentBeanIF{
    @PersistenceContext(unitName="default")
    private EntityManager entityManager;
    @Override
    public String getName(String matriculation) {
        Query q=entityManager.createQuery("Select * From STUDENT where MATRICULATION = "+matriculation);
        Student s=(Student)(q.getSingleResult());
        return s.getName();
    }
    @Override
    public String getSurname(String matriculation) {
        Query q=entityManager.createQuery("Select * From STUDENT where MATRICULATION = "+matriculation);
        Student s=(Student)(q.getSingleResult());
        return s.getSurname();
    }
    @Override
    public Integer getMatriculation(int i) {
        Query q=entityManager.createQuery("Select * From STUDENT where ID = "+i);
        Student s=(Student)(q.getSingleResult());
        return s.getMatriculation();
    }
    @Override
    public Student getStudent(String matriculation) {
        Query q=entityManager.createQuery("Select * From STUDENT where MATRICULATION = "+matriculation);
        Student s=(Student)(q.getSingleResult());
        return s;
    }
    @Override
    public Collection<Course> getCourses(String matriculation) {
        Query q=entityManager.createQuery("Select * From STUDENT where MATRICULATION = "+matriculation);
        Student s=(Student)(q.getSingleResult());
        return s.getCourses();
    }
}
