package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Address;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToManyAssociations {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();
            Customer customer = new Customer("Cata", "Friend");
            customer.setPaymentMethod("Nequi");

            Address address1 = new Address("Street caracas", 30);
            Address address2 = new Address("Avenue Jimenez", 13);

            customer.getAddresses().add(address1);
            customer.getAddresses().add(address2);

            em.persist(customer);

            em.getTransaction().commit();

            System.out.println(customer);

            //Como se trata de nueva transaccion, se coloca de nuevo begin y commit
            em.getTransaction().begin();
            customer = em.find(Customer.class, customer.getId());
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
