package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Course;
import org.codexdei.hibernateapp.entity.Student;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateManyToManyAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Student student1 = new Student("Vegueta", "Sayayin");
            Student student2 = new Student("Saske", "Uchija");

            Course course1 = new Course("Tecnologia", "Lucho");
            Course course2 = new Course("Ingles", "Aleja");
            Course course3 = new Course("Musica", "Eli");

            student1.getCourses().add(course1);
            student1.getCourses().add(course2);

            student2.getCourses().add(course1);
            student2.getCourses().add(course2);
            student2.getCourses().add(course3);

            em.persist(student1);
            em.persist(student2);

            em.getTransaction().commit();

            System.out.println(student1);
            System.out.println(student2);

            //Borrar un curso al alumno, no el curso por completo porque otros alumnos pueden estar usando este curso
            em.getTransaction().begin();
            Course c9 = em.find(Course.class,9L);
            student2.getCourses().remove(c9);
            em.getTransaction().commit();

            System.out.println(student2);


        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {

            em.close();
        }
    }
}
