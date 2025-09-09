package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.CustomerDetail;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToOneFindAssociationsBidirectional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class,10L);

            CustomerDetail detail = new CustomerDetail(true,5000L);

            //como la relacion es bidireccional se guarda en cada uno
            customer.addDetail(detail);

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
