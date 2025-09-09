package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.CustomerDetail;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToOneFindAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Customer customer = em.find(Customer.class,7L);

            CustomerDetail detail = new CustomerDetail(true,5000L);
            em.persist(detail);

            customer.setDetail(detail);
            em.getTransaction().commit();

            System.out.println(customer);

        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            em.close();
        }
    }
}
