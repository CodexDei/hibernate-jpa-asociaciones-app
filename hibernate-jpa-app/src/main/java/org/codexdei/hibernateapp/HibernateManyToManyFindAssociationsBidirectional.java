package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Course;
import org.codexdei.hibernateapp.entity.Student;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateManyToManyFindAssociationsBidirectional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Student student1 = em.find(Student.class,1L);
            Student student2 = em.find(Student.class,2L);
            Student student3 = em.find(Student.class,3L);

            Course course1 = em.find(Course.class,1L);//new Course("Historia", "Melvin");
            Course course2 = em.find(Course.class,2L);//new Course("Calculo", "Jeysson");
            Course course3 = em.find(Course.class,3L);//new Course("Geografia", "Robinson");
            Course course4 = em.find(Course.class,4L);//new Course("Geografia", "Robinson");
            Course course5 = em.find(Course.class,5L);//new Course("Geografia", "Robinson");
            Course course6 = em.find(Course.class,6L);//new Course("Geografia", "Robinson");

            student1.addCourse(course1);
            student1.addCourse(course2);
            student1.addCourse(course4);

            student2.addCourse(course1);
            student2.addCourse(course3);
            student2.addCourse(course5);

            student3.addCourse(course1);
            student3.addCourse(course2);
            student3.addCourse(course3);
            student3.addCourse(course4);
            student3.addCourse(course5);
            student3.addCourse(course6);

            //no es necesario merge porque el commit lo actualiza
//            em.merge(student1);
//            em.merge(student2);
//            em.merge(student3);

            em.getTransaction().commit();

            System.out.println(student1);
            System.out.println(student2);
            System.out.println(student3);

            //Borrar un curso al alumno, no el curso por completo porque otros alumnos pueden estar usando este curso
            em.getTransaction().begin();
            Course removeCourse = em.find(Course.class,5L);
            student2.removeCourse(removeCourse);
            em.getTransaction().commit();

            System.out.println("************* REMOVE **********************");

            System.out.println(student1);
            System.out.println(student2);
            System.out.println(student3);


        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {

            em.close();
        }
    }
}
