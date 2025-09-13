package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Student;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List<Student> students = em.createQuery("select distinct s from Student s left outer join fetch s.courses",Student.class).getResultList();
        students.forEach(s -> System.out.println(s.getName() + ", courses: " + s.getCourses()));
        em.close();
    }
}
