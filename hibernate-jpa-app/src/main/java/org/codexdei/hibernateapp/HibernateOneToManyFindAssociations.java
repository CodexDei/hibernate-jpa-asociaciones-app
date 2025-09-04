package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Addresses;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToManyFindAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, 2L);

            Addresses addresses1 = new Addresses("Street caracas", 30);
            Addresses addresses2 = new Addresses("Avenue Jimenez", 13);

            customer.getAddresses().add(addresses1);
            customer.getAddresses().add(addresses2);

            em.merge(customer);

            em.getTransaction().commit();

            System.out.println(customer);

            //nueva transaccion, por tanto otro begin y otro commit
            em.getTransaction().begin();
            addresses1 = em.find(Addresses.class,1L);
            customer.getAddresses().remove(addresses1);
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
