package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        //No se usa try y catch porque son solo consultas, el try y catch son para transacciones
        //Esta consulta personalizada evita hacer varias consultas, en una sola consulta se obtiene toda la
        //informacion
        Customer customer = em.createQuery("select c from Customer c left outer join fetch c.addresses left join fetch c.detail where c.id=:id", Customer.class)
                .setParameter("id", 1L)
                .getSingleResult();

        System.out.println(customer.getName() + ", Address:" + customer.getAddresses());
        System.out.println(customer.getName() + ", Detail:" + customer.getDetail());

        em.close();
    }
}
