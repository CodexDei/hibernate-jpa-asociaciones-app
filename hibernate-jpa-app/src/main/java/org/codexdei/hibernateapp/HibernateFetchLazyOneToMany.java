package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToMany {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        //No se usa try y catch porque son solo consultas, el try y catch son para transacciones
        Customer customer = em.find(Customer.class, 1L);

        System.out.println(customer.getName() + "Address:" + customer.getAddresses());

        em.close();
    }
}
