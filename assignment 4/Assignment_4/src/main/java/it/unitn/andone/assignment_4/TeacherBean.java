package it.unitn.andone.assignment_4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(TeacherBeanIF.class)
public class TeacherBean implements TeacherBeanIF{

    @PersistenceContext(unitName="default")
    private EntityManager entityManager;

    @Override
    public String getName(int i) {
        Query q=entityManager.createQuery("Select * From TEACHER where ID = "+i);
        Teacher t=(Teacher)(q.getSingleResult());
        return t.getName();
    }

    @Override
    public String getSurname(int i) {
        Query q=entityManager.createQuery("Select * From TEACHER where ID = "+i);
        Teacher t=(Teacher)(q.getSingleResult());
        return t.getSurname();
    }

    @Override
    public Teacher getTeacher(int i) {
        Query q=entityManager.createQuery("Select * From TEACHER where ID = "+i);
        Teacher t=(Teacher)(q.getSingleResult());
        return t;
    }
}
