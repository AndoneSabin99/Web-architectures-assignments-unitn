package it.unitn.andone.assignment_4;

import java.util.Collection;

public interface StudentBeanIF {
    String getName(String matriculation);
    String getSurname(String matriculation);
    Integer getMatriculation(int i);
    Student getStudent(String matriculation);
    Collection<Course> getCourses(String matriculation);
}
