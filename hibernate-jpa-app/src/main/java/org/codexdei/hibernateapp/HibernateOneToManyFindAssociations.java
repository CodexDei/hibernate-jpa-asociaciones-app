package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Address;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToManyFindAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, 2L);

            Address address1 = new Address("Street caracas", 30);
            Address address2 = new Address("Avenue Jimenez", 13);

            customer.getAddresses().add(address1);
            customer.getAddresses().add(address2);

            em.merge(customer);

            em.getTransaction().commit();

            System.out.println(customer);

            //nueva transaccion, por tanto otro begin y otro commit
            em.getTransaction().begin();
            address1 = em.find(Address.class,1L);
            customer.getAddresses().remove(address1);
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
