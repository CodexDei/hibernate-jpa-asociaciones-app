package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Course;
import org.codexdei.hibernateapp.entity.Student;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateManyToManyAssociationsBidirectional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Student student4 = new Student("Vegueta", "Sayayin");
            Student student5 = new Student("Saske", "Uchija");

            Course course7 = new Course("Tecnologia", "Lucho");
            Course course8 = new Course("Ingles", "Aleja");
            Course course9 = new Course("Musica", "Eli");

            student4.addCourse(course7);
            student4.addCourse(course8);
            student4.addCourse(course9);

            student5.addCourse(course7);
            student5.addCourse(course9);

            em.persist(student4);
            em.persist(student5);

            em.getTransaction().commit();

            System.out.println(student4);
            System.out.println(student5);

            //Borrar un curso al alumno, no el curso por completo porque otros alumnos pueden estar usando este curso
            em.getTransaction().begin();
            //Course c9 = em.find(Course.class,9L);
            Course c1 = new Course("Tecnologia", "Lucho");
            c1.setId(7L);
            student5.removeCourse(c1);
            em.getTransaction().commit();

            System.out.println(student5);


        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {

            em.close();
        }
    }
}
