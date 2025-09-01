package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.Invoice;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateManyToOneFindAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Customer c = em.find(Customer.class,1L);
            c.setPaymentMethod("debito");
            em.persist(c);

            Invoice i = new Invoice("Buy office items",100L);
            i.setCustomer(c);
            em.persist(i);

            System.out.println(i);
            em.getTransaction().commit();

        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            em.close();
        }


    }
}
