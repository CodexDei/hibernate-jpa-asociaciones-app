package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.CustomerDetail;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToOneAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Customer customer = new Customer("Clark","Kent");
            customer.setPaymentMethod("credito");
            em.persist(customer);

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
